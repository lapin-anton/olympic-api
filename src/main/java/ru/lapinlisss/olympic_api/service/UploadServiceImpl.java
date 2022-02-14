package ru.lapinlisss.olympic_api.service;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.model.*;
import ru.lapinlisss.olympic_api.repository.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Override
    public void store(MultipartFile file) {
        List<String[]> rows = getParsedRows(file);
        List<Result> results = rows.stream()
                .map(this::processResult)
                .collect(Collectors.toList());

        List<Result> bufferedResults;
        long startTime = new Date().getTime();
        long lastTime = startTime;
        long items = 0;
        while(!results.isEmpty()) {
            if (results.size() >= 5000) {
                bufferedResults = results.subList(0, 4999);
            } else {
                bufferedResults = results;
            }
            resultRepository.saveAllAndFlush(bufferedResults);
            results.removeAll(bufferedResults);
            long currentTime = new Date().getTime();
            items += bufferedResults.size();
            log.info("During time {} seconds restored {} items into db.", (currentTime - lastTime) / 1000, items);
            lastTime = currentTime;
        }

        log.info("Total restoring time: {} seconds", (lastTime - startTime) / 1000);
        log.info("Total restored items: {}", items);
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

    private Result processResult(String[] row) {

        Game game = processGame(row);

        Sport sport = processSport(row);

        Country country = processCountry(row);

        Athlete athlete = processAthlete(country, row);

        return Result.builder()
                .game(game)
                .sport(sport)
                .athlete(athlete)
                .athleteRank(Integer.parseInt(row[4]))
                .athleteAge(row[7].equals("None") ? null : Integer.parseInt(row[7]))
                .gold(Integer.parseInt(row[9]))
                .silver(Integer.parseInt(row[10]))
                .bronze(Integer.parseInt(row[11]))
                .total(Integer.parseInt(row[12]))
                .build();
    }

    private Game processGame(String[] row) {
        Game game = Game.builder()
                .type(row[3])
                .year(Integer.parseInt(row[2]))
                .build();

        Optional<Game> gameFromDb = gameRepository.findGameByTypeAndYear(game.getType(), game.getYear());

        if (gameFromDb.isPresent())
            game = gameFromDb.get();
        else
            game = gameRepository.save(game);

        return game;
    }

    private Sport processSport(String[] row) {
        Sport sport = Sport.builder()
                .name(row[8])
                .build();

        Optional<Sport> sportFromDb = sportRepository.findSportByName(sport.getName());

        if (sportFromDb.isPresent())
            sport = sportFromDb.get();
        else
            sport = sportRepository.save(sport);

        return sport;
    }

    private Country processCountry(String[] row) {
        Country country = Country.builder()
                .trigger(row[0])
                .name(row[1])
                .build();

        Optional<Country> countryFromDb = countryRepository.findCountryByTrigger(country.getTrigger());

        if (countryFromDb.isPresent())
            country = countryFromDb.get();
        else
            country = countryRepository.save(country);

        return country;
    }

    private Athlete processAthlete(Country country, String[] row) {
        Athlete athlete = Athlete.builder()
                .country(country)
                .name(row[5].split(",").length > 1 ? row[5].split(",")[1] : row[5].split(",")[0])
                .surname(row[5].split(",").length > 1 ? row[5].split(",")[0] : null)
                .gender(row[6])
                .url(row[13])
                .build();

        Optional<Athlete> athleteFromDb =
                athleteRepository.findAthleteByNameAndUrl(athlete.getName(), athlete.getUrl());

        if (athleteFromDb.isPresent())
            athlete = athleteFromDb.get();
        else
            athlete = athleteRepository.save(athlete);

        return athlete;
    }
}
