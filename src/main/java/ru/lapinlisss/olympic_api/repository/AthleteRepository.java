package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Country;
import ru.lapinlisss.olympic_api.model.entity.Result;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    List<Athlete> findAllByNameOrSurname(String name, String surname);

    List<Athlete> findAllByCountry(Country country);

}
