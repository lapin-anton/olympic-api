package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.ResultDto;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.model.response.CountryTeamRatingItem;
import ru.lapinlisss.olympic_api.service.ResultService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("results")
@RequiredArgsConstructor
public class ResultsController {

    private final ResultService resultService;

    // get all by game
    @Transactional
    @GetMapping("/game/all")
    public ResponseEntity<List<ResultDto>> getAllResults(@RequestParam String type, @RequestParam int year, @RequestParam int page) {
        log.info("Income request to get all results by game type {} year {}", type, year);
        List<Result> results = resultService.getAllResultsByGame(type, year, page);
        List<ResultDto> resultDtos = results.stream()
                .map(CustomMapper.INSTANCE::mapResultToResultDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(resultDtos);
    }

    // get country team's rating by game id
    @Transactional
    @GetMapping("/team/rating/game/{id}")
    public ResponseEntity<List<CountryTeamRatingItem>> getCountryTeamRatingByGameId(@PathVariable Long id) {
        log.info("Income request to get country team's rating by game id {}", id);
        List<CountryTeamRatingItem> countryTeamRatingByGame = resultService.getCountryTeamRatingByGame(id);
        return ResponseEntity.ok().body(countryTeamRatingByGame);
    }
}
