package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.response.GameAthletesCount;

public interface GameAthleteCountRepository extends JpaRepository<GameAthletesCount, Long> {
}
