package ru.lapinlisss.olympic_api.service;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.model.*;
import ru.lapinlisss.olympic_api.repository.*;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
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

    private static AtomicLong counter = new AtomicLong(0L);

    @Override
    public void store(MultipartFile file) {
        List<String[]> rows = getParsedRows(file);
        List<Result> results = rows.stream()
                .map(this::processResult)
                .collect(Collectors.toList());
    }

    private List<String[]> getParsedRows(MultipartFile file) {
        List<String[]> parsedRows = null;
        try (Reader inputReader = new InputStreamReader(file.getInputStream(), "UTF-8")) {
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
        Game game = Game.builder()
                .type(row[3])
                .year(Integer.parseInt(row[2]))
                .build();

        Optional<Game> gameFromDb = gameRepository.findGameByTypeAndYear(game.getType(), game.getYear());

        if (gameFromDb.isPresent())
            game = gameFromDb.get();
        else
            game = gameRepository.save(game);

        Sport sport = Sport.builder()
                .name(row[8])
                .build();

        Optional<Sport> sportFromDb = sportRepository.findSportByName(sport.getName());

        if (sportFromDb.isPresent())
            sport = sportFromDb.get();
        else
            sport = sportRepository.save(sport);

        Country country = Country.builder()
                .trigger(row[0])
                .name(row[1])
                .build();

        Optional<Country> countryFromDb = countryRepository.findCountryByTrigger(country.getTrigger());

        if (countryFromDb.isPresent())
            country = countryFromDb.get();
        else
            country = countryRepository.save(country);

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

        Result result = Result.builder()
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
        if(counter.addAndGet(1L) % 100 == 0 && counter.get() > 0) {
            log.info("stored {} records into result table", counter);
        }
        return resultRepository.save(result);
    }
}
