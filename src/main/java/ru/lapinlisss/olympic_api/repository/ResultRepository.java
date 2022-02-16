package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByGame(Game game);

}
