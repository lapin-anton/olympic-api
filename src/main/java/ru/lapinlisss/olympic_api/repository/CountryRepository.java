package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.entity.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByTrigger(String trigger);

    Optional<Country> findCountryByNameIgnoreCase(String name);

}
