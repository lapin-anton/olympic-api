package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Country;
import ru.lapinlisss.olympic_api.repository.AthleteRepository;
import ru.lapinlisss.olympic_api.repository.CountryRepository;
import ru.lapinlisss.olympic_api.repository.GameRepository;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final CountryRepository countryRepository;

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

    @Override
    public List<Athlete> getAthletesByCountry(String country) {

        List<Athlete> athletes = new ArrayList<>();

        Optional<Country> countryOptional = countryRepository.findCountryByName(country);

        if (countryOptional.isPresent()) {
            athletes = athleteRepository.findAllByCountry(countryOptional.get());
        }

        return athletes;
    }

}
