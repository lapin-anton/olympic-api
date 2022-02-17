package ru.lapinlisss.olympic_api.service;

import ru.lapinlisss.olympic_api.model.entity.Athlete;

import java.util.List;

public interface AthleteService {

    Athlete getAthleteById(Long id);

    List<Athlete> findAllAthletes(int page);

    List<Athlete> getAthletesByName(String name, String surname);

    List<Athlete> getAthletesByCountry(String country, int page);

    List<Athlete> getAthletesByGame(String type, int year, int page);

    List<Athlete> getAthletesByGameAndCountry(String type, int year, String country);
}
