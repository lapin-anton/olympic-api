package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
