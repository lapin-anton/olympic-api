package ru.lapinlisss.olympic_api.service;

import ru.lapinlisss.olympic_api.model.entity.Athlete;

import java.util.List;

public interface AthleteService {

    Athlete getAthleteById(Long id);

    List<Athlete> findAllAthletes();

    List<Athlete> getAthletesByName(String name, String surname);

    List<Athlete> getAthletesByCountry(String country, int page);

    List<Athlete> getAthletesByGame(String type, int year, int page);

}
