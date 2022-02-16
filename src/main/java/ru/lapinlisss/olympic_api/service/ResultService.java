package ru.lapinlisss.olympic_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;

public interface ResultService {

    Page<Result> getResultsByGame(Game game, Pageable pageable);

}
