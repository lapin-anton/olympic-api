package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.response.GameSportsCount;

public interface GameSportsCountRepository extends JpaRepository<GameSportsCount, Long> {
}
