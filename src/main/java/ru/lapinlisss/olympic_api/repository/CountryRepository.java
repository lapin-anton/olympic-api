package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
