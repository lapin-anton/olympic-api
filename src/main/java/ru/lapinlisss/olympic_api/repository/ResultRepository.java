package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Page<Result> findAllByGame(Game game, Pageable pageable);

}
