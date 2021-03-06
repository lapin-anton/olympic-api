package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Country;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.repository.AthleteRepository;
import ru.lapinlisss.olympic_api.repository.CountryRepository;
import ru.lapinlisss.olympic_api.repository.GameRepository;

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
    private final GameRepository gameRepository;

    private final GameService gameService;
    private final ResultService resultService;

    @Override
    public Athlete getAthleteById(Long id) {
        return athleteRepository.getById(id);
    }

    @Override
    public List<Athlete> findAllAthletes(int page) {
        Pageable pageable = PageRequest.of(page, 100);

        return athleteRepository.findAll(pageable).toList();
    }

    @Override
    public List<Athlete> getAthletesByName(String name, String surname) {
        return athleteRepository.findAllByNameOrSurname(name, surname);
    }

    @Override
    public List<Athlete> getAthletesByCountry(String country, int page) {

        List<Athlete> athletes = new ArrayList<>();

        Optional<Country> countryOptional = countryRepository.findCountryByNameIgnoreCase(country);

        Pageable pageable = PageRequest.of(page, 20);

        if (countryOptional.isPresent()) {
            athletes = athleteRepository.findAllByCountry(countryOptional.get(), pageable).toList();
        }

        return athletes;
    }

    @Override
    public List<Athlete> getAthletesByGame(long id, int page) {

        List<Athlete> athletes = new ArrayList<>();

        Optional<Game> gameOptional = gameRepository.findById(id);

        Game game = null;

        if (gameOptional.isPresent()) {
            game = gameOptional.get();
        }

        Pageable pageable = PageRequest.of(page, 100);

        if (game != null) {
            Page<Result> results = resultService.getResultsByGame(game, pageable);
            List<Athlete> athletesPrep = results.stream().map(Result::getAthlete)
                    .collect(Collectors.toList());

            athletes = new ArrayList<>(new LinkedHashSet<>(athletesPrep));
        }

        return athletes;
    }

    @Override
    public List<Athlete> getAthletesByGame(long id) {

        List<Athlete> athletes = new ArrayList<>();

        Optional<Game> gameOptional = gameRepository.findById(id);

        Game game = null;

        if (gameOptional.isPresent()) {
            game = gameOptional.get();
        }

        if (game != null) {
            List<Result> results = game.getResults();

            List<Athlete> athletesPrep = results.stream().map(Result::getAthlete)
                    .collect(Collectors.toList());

            athletes = new ArrayList<>(new LinkedHashSet<>(athletesPrep));
        }

        return athletes;
    }

    @Override
    public List<Athlete> getAthletesByGameAndCountry(String type, int year, String countryName) {
        List<Athlete> athletes = new ArrayList<>();

        Optional<Country> countryOptional = countryRepository.findCountryByNameIgnoreCase(countryName);

        if (countryOptional.isPresent()) {
            List<Athlete> athletesByCountry = athleteRepository.findAllByCountry(countryOptional.get());

            Game game = gameService.getGameByTypeAndYear(type, year);

            if (game != null) {
                athletes = athletesByCountry.stream().filter(athlete -> {
                    for (Result r: athlete.getResults()) {
                        if (r.getGame().getType().equals(type) && r.getGame().getYear() == year)
                            return true;
                    }
                    return false;
                }).collect(Collectors.toList());
            }
        }

        return athletes;
    }

    @Override
    public Athlete putImageUrlForAthlete(long id, String url) {

        Athlete athlete = athleteRepository.getById(id);

        if(athlete != null) {
            athlete.setUrl(url);
        }

        return athleteRepository.saveAndFlush(athlete);
    }

}
