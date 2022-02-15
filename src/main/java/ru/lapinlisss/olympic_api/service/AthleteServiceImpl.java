package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.repository.AthleteRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;

    @Override
    public Athlete getAthleteById(Long id) {
        return athleteRepository.getById(id);
    }

    @Override
    public List<Athlete> findAllAthletes() {
        return athleteRepository.findAll();
    }

    @Override
    public List<Athlete> getAthletesByName(String name, String surname) {
        return athleteRepository.findAllByNameOrSurname(name, surname);
    }

}
