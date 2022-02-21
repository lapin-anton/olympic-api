package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.GameDto;
import ru.lapinlisss.olympic_api.model.dto.SportDto;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Sport;
import ru.lapinlisss.olympic_api.repository.SportRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("sport")
@RequiredArgsConstructor
public class SportController {

    private final SportRepository sportRepository;

    // get all
    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<SportDto>> getAllSports() {
        log.info("Income request to get all sports");
        List<Sport> sports = sportRepository.findAll();

        sports.sort(Comparator.comparing(Sport::getName));

        List<SportDto> sportDtos = sports.stream()
                .map(CustomMapper.INSTANCE::mapSportToSportDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(sportDtos);
    }

}
