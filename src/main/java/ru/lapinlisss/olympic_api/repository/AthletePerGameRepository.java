package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.response.AthletePerGame;

import java.util.List;

public interface AthletePerGameRepository extends JpaRepository<AthletePerGame, Long> {

    List<AthletePerGame> findAllByGameId(long gameId);

}
