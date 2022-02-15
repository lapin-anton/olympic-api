package ru.lapinlisss.olympic_api.service;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.exception.UploadException;
import ru.lapinlisss.olympic_api.model.entity.*;
import ru.lapinlisss.olympic_api.repository.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final CountryRepository countryRepository;
    private final AthleteRepository athleteRepository;
    private final GameRepository gameRepository;
    private final SportRepository sportRepository;
    private final ResultRepository resultRepository;


    private static final int BUFFER_SIZE = 2000;

    @Override
    public String store(MultipartFile file) throws UploadException {
        String report;
        try {
            log.info("Storing started...");
            long start = new Date().getTime();
            List<String[]> rows = getParsedRows(file);
            storeGames(rows);
            storeSports(rows);
            storeCountries(rows);
            storeAthletes(rows);
            storeResults(rows);
            long finish = new Date().getTime();
            report = String.format("During %d seconds data storing has been complete!", (finish - start) / 1000);
            log.info(report);
        } catch (Exception e) {
            throw new UploadException("File uploading went with errors");
        }
        return report;
    }

    private void storeGames(List<String[]> rows) {
        log.info("Start games storing...");
        long start = new Date().getTime();
        List<Game> gamesPrep = rows.stream().map(row ->
                Game.builder()
                        .type(row[3])
                        .year(Integer.parseInt(row[2]))
                        .build()
        ).collect(Collectors.toList());

        List<Game> games = new ArrayList<>(new LinkedHashSet<>(gamesPrep));
        gameRepository.saveAllAndFlush(games);
        long finish = new Date().getTime();
        log.info("During {} seconds stored {} game items in db", (finish - start) / 1000, games.size());
        log.info("Games storing completed");
    }

    private void storeSports(List<String[]> rows) {
        log.info("Start sports storing...");
        long start = new Date().getTime();
        List<Sport> sportsPrep = rows.stream().map(row ->
                Sport.builder()
                        .name(row[8])
                        .build()
        ).collect(Collectors.toList());

        List<Sport> sports = new ArrayList<>(new LinkedHashSet<>(sportsPrep));
        sportRepository.saveAllAndFlush(sports);
        long finish = new Date().getTime();
        log.info("During {} seconds stored {} sport items in db", (finish - start) / 1000, sports.size());
        log.info("sports storing completed");
    }

    private void storeCountries(List<String[]> rows) {
        log.info("Start countries storing...");
        long start = new Date().getTime();
        List<Country> countriesPrep = rows.stream().map(row ->
                Country.builder()
                        .trigger(row[0])
                        .name(row[1])
                        .build()
        ).collect(Collectors.toList());

        List<Country> countries = new ArrayList<>(new LinkedHashSet<>(countriesPrep));
        countryRepository.saveAllAndFlush(countries);
        long finish = new Date().getTime();
        log.info("During {} seconds stored {} country items in db", (finish - start) / 1000, countries.size());
        log.info("countries storing completed");
    }

    private void storeAthletes(List<String[]> rows) {
        log.info("Start athletes storing...");

        List<Country> countries = countryRepository.findAll();

        Map<String, Country> countryMap = new HashMap<>();

        countries.forEach(country -> countryMap.put(country.getTrigger(), country));

        long start = new Date().getTime();
        List<Athlete> athletesPrep = rows.stream().map(row ->
                Athlete.builder()
                        .country(countryMap.get(row[0]))
                        .name(row[5].split(",").length > 1 ? row[5].split(",")[1] : row[5].split(",")[0])
                        .surname(row[5].split(",").length > 1 ? row[5].split(",")[0] : null)
                        .gender(row[6])
                        .url(row[13])
                        .build()
        ).collect(Collectors.toList());

        List<Athlete> athletes = new ArrayList<>(new LinkedHashSet<>(athletesPrep));
        long counter = 0;
        for (int i = 0; i <= athletes.size() / BUFFER_SIZE; i++) {
            int index = i * BUFFER_SIZE;
            if (index + BUFFER_SIZE < athletes.size()) {
                athleteRepository.saveAllAndFlush(athletes.subList(index, index + BUFFER_SIZE));
                long last = new Date().getTime();
                counter += BUFFER_SIZE;
                log.info("During {} seconds stored {} athlete items in db", (last - start) / 1000, counter);
            } else {
                athleteRepository.saveAllAndFlush(athletes.subList(index, athletes.size()));
            }
        }

        long finish = new Date().getTime();
        log.info("During {} seconds stored {} athlete items in db", (finish - start) / 1000, athletes.size());
        log.info("Athletes storing completed");
    }

    private void storeResults(List<String[]> rows) {
        log.info("Start results storing...");

        List<Game> games = gameRepository.findAll();

        List<Sport> sports = sportRepository.findAll();

        List<Athlete> athletes = athleteRepository.findAll();

        Map<String, Game> gameMap = new HashMap<>();

        games.forEach(game -> gameMap.put(String.format("%s %d", game.getType(), game.getYear()), game));

        Map<String, Sport> sportMap = new HashMap<>();

        sports.forEach(sport -> sportMap.put(sport.getName(), sport));

        Map<String, Athlete> athleteMap = new HashMap<>();

        athletes.forEach(athlete -> athleteMap.put(athlete.getUrl(), athlete));

        long start = new Date().getTime();

        List<Result> results = rows.stream().map(row ->
                Result.builder()
                        .game(gameMap.get(String.format("%s %s", row[3], row[2])))
                        .sport(sportMap.get(row[8]))
                        .athlete(athleteMap.get(row[13]))
                        .athleteRank(Integer.parseInt(row[4]))
                        .athleteAge(row[7].equals("None") ? null : Integer.parseInt(row[7]))
                        .gold(Integer.parseInt(row[9]))
                        .silver(Integer.parseInt(row[10]))
                        .bronze(Integer.parseInt(row[11]))
                        .total(Integer.parseInt(row[12]))
                        .build()
        ).collect(Collectors.toList());
        long counter = 0;
        for (int i = 0; i <= results.size() / BUFFER_SIZE; i++) {
            int index = i * BUFFER_SIZE;
            if (index + BUFFER_SIZE < results.size()) {
                resultRepository.saveAllAndFlush(results.subList(index, index + BUFFER_SIZE));
                long last = new Date().getTime();
                counter += BUFFER_SIZE;
                log.info("During {} seconds stored {} result items in db", (last - start) / 1000, counter);
            } else {
                resultRepository.saveAllAndFlush(results.subList(index, results.size()));
            }
        }

        long finish = new Date().getTime();
        log.info("During {} seconds stored {} result items in db", (finish - start) / 1000, results.size());
        log.info("Results storing completed");
    }

    private List<String[]> getParsedRows(MultipartFile file) {
        List<String[]> parsedRows = null;
        try (Reader inputReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            TsvParser parser = new TsvParser(new TsvParserSettings());
            parsedRows = parser.parseAll(inputReader);
        } catch (IOException e) {
            log.info("Error when file parsing: {}", e.getMessage());
        }
        if (parsedRows != null && !parsedRows.isEmpty()) {
            parsedRows.remove(0); // remove title row of table
        }
        return parsedRows;
    }

}
