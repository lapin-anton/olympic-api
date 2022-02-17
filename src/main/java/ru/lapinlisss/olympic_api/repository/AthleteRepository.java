package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Country;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Page<Athlete> findAll(Pageable pageable);

    List<Athlete> findAllByNameOrSurname(String name, String surname);

    Page<Athlete> findAllByCountry(Country country, Pageable pageable);

}
