package ru.lapinlisss.olympic_api.service;

import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;

import java.util.List;

public interface ResultService {

    List<Result> getResultsByGame(Game game);

}
