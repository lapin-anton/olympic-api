package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.AthleteDto;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.service.AthleteService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("athlete")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    // get all
    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<AthleteDto>> getAllAthletes() {
        log.info("Income request to get all athletes");
        List<Athlete> athletes = athleteService.findAllAthletes();
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
    @GetMapping("/country/{country}")
    public ResponseEntity<List<AthleteDto>> getAthletesByCountry(@PathVariable String country) {
        log.info("Income request to get athletes by country {}", country);
        List<Athlete> athletes = athleteService.getAthletesByCountry(country);

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }

    // get athletes by game
    @Transactional
    @GetMapping("/game")
    public ResponseEntity<List<AthleteDto>> getAthletesByGame(@RequestParam String type, @RequestParam int year) {
        log.info("Income request to get athletes by game type {} and year {}", type, year);
        List<Athlete> athletes = athleteService.getAthletesByGame(type, year);

        List<AthleteDto> athleteDtos = athletes.stream()
                .map(CustomMapper.INSTANCE::mapAthleteToAthleteDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(athleteDtos);
    }
    // get athletes by country, game and sport

    // ...

}
