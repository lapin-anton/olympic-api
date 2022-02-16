package ru.lapinlisss.olympic_api.service;

import ru.lapinlisss.olympic_api.model.entity.Game;

public interface GameService {

    Game getGameByTypeAndYear(String type, int year);

}
