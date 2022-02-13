package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.Athlete;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
