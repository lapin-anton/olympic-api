package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.AthleteDto;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.service.AthleteService;

@Slf4j
@RestController
@RequestMapping("athlete")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    // get all


    // get athlete by id
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<AthleteDto> getAthlete(@PathVariable Long id) {
        log.info("Income request to get athlete by id {}", id);
        Athlete athlete = athleteService.getAthleteById(id);
        return athlete != null
                ? ResponseEntity.ok().body(CustomMapper.INSTANCE.mapAthleteToAthleteDto(athlete))
                : ResponseEntity.notFound().build();
    }

    // get athlete by name

    // get athletes by country

    // get athletes by country and game

    // get athletes by country, game and sport

    // ...

}
