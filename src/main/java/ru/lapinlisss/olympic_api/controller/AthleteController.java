package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.AthleteDto;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.response.AthletePerGame;
import ru.lapinlisss.olympic_api.repository.AthletePerGameRepository;
import ru.lapinlisss.olympic_api.service.AthleteService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("athlete")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    private final AthletePerGameRepository athletePerGameRepository;

    // get all
    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<AthleteDto>> getAllAthletes(@RequestParam int page) {
        log.info("Income request to get all athletes");
        List<Athlete> athletes = athleteService.findAllAthletes(page);
        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // get athlete by id
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<AthleteDto> getAthleteById(@PathVariable Long id) {
        log.info("Income request to get athlete by id {}", id);
        Athlete athlete = athleteService.getAthleteById(id);
        return athlete != null
                ? ResponseEntity.ok().body(CustomMapper.INSTANCE.mapAthleteToAthleteDto(athlete))
                : ResponseEntity.notFound().build();
    }

    // get athlete by name
    @Transactional
    @GetMapping("")
    public ResponseEntity<List<AthleteDto>> getAthletesByName(@RequestParam String name, @RequestParam String surname) {
        log.info("Income request to get athletes by name {} and surname {}", name, surname);
        List<Athlete> athletes = athleteService.getAthletesByName(name, surname);

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // get athletes by country
    @Transactional
    @GetMapping("/country")
    public ResponseEntity<List<AthleteDto>> getAthletesByCountry(@RequestParam String country, @RequestParam int page) {
        log.info("Income request to get athletes by country {}", country);
        List<Athlete> athletes = athleteService.getAthletesByCountry(country, page);

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // get athletes by game id
    @Transactional
    @GetMapping("/game")
    public ResponseEntity<List<AthleteDto>> getAthletesByGame(@RequestParam long id, @RequestParam int page) {
        log.info("Income request to get athletes by game id {}", id);

        List<Athlete> athletes;

        if(page != -1) {
            athletes = athleteService.getAthletesByGame(id, page);
        } else {
            athletes = athleteService.getAthletesByGame(id);
        }

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // get top 50 athletes by game id
    @Transactional
    @GetMapping("/game/{id}/top50")
    public ResponseEntity<List<AthletePerGame>> getTop50AthletesByGame(@PathVariable long id) {
        log.info("Income request to get top 50 athletes by game id {}", id);

        List<AthletePerGame> athletes = athletePerGameRepository.findAllByGameId(id);

        athletes.sort(Comparator.comparing(AthletePerGame::getGold)
                .thenComparing(AthletePerGame::getSilver)
                .thenComparing(AthletePerGame::getBronze));

        Collections.reverse(athletes);

        return ResponseEntity.ok().body(athletes.subList(0, 50));
    }

    // get athletes by game and country
    @Transactional
    @GetMapping("/game/country")
    public ResponseEntity<List<AthleteDto>> getAthletesByGameAndCountry(@RequestParam String type, @RequestParam int year, @RequestParam String country) {
        log.info("Income request to get athletes by game type {} and year {}", type, year);
        List<Athlete> athletes = athleteService.getAthletesByGameAndCountry(type, year, country);

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // put athlete's image url
    @Transactional
    @PostMapping("/{id}/img")
    public ResponseEntity<AthleteDto> putImageUrl(@PathVariable long id, @RequestParam String url) {
        log.info("Income request to put athlete's image url {} for athlete with id {}", url, id);
        return ResponseEntity.ok()
                .body(CustomMapper.INSTANCE.mapAthleteToAthleteDto(athleteService.putImageUrlForAthlete(id, url)));
    }

}
