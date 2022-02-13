package ru.lapinlisss.olympic_api.service;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.model.*;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public void store(MultipartFile file) {
        List<String[]> rows = getParsedRows(file);
        List<Result> results = rows.stream()
                .map(this::processResult)
                .collect(Collectors.toList());
        resultRepository.saveAll(results);
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
        return Result.builder()
                .game(Game.builder()
                        .type(row[3])
                        .year(Integer.parseInt(row[2]))
                        .build())
                .sport(Sport.builder()
                        .name(row[8])
                        .build())
                .athlete(Athlete.builder()
                        .country(Country.builder()
                                .trigger(row[0])
                                .name(row[1])
                                .build())
                        .name(row[5].split(",").length == 2 ? row[5].split(",")[1] : "N/A")
                        .surname(row[5].split(",")[0])
                        .gender(row[6])
                        .url(row[13])
                        .build())
                .athleteRank(Integer.parseInt(row[4]))
                .athleteAge(row[7].equals("None") ? -1 : Integer.parseInt(row[7]))
                .gold(Integer.parseInt(row[9]))
                .silver(Integer.parseInt(row[10]))
                .bronze(Integer.parseInt(row[11]))
                .total(Integer.parseInt(row[12]))
                .build();
    }
}
