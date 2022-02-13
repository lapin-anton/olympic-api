package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
