package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Country;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.repository.AthleteRepository;
import ru.lapinlisss.olympic_api.repository.CountryRepository;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final CountryRepository countryRepository;

    private final GameService gameService;
    private final ResultService resultService;

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

        Optional<Country> countryOptional = countryRepository.findCountryByNameIgnoreCase(country);

        if (countryOptional.isPresent()) {
            athletes = athleteRepository.findAllByCountry(countryOptional.get());
        }

        return athletes;
    }

    @Override
    public List<Athlete> getAthletesByGame(String type, int year) {

        List<Athlete> athletes = new ArrayList<>();

        Game game = gameService.getGameByTypeAndYear(type, year);

        if (game != null) {
            List<Result> results = resultService.getResultsByGame(game);
            List<Athlete> athletesPrep = results.stream().map(Result::getAthlete)
                    .collect(Collectors.toList());

            athletes = new ArrayList<>(new LinkedHashSet<>(athletesPrep));
        }

        return athletes;
    }


}
