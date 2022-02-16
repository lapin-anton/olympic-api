package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.repository.GameRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game getGameByTypeAndYear(String type, int year) {
        Game game = null;

        Optional<Game> optionalGame = gameRepository.findGameByTypeAndYear(type, year);

        if (optionalGame.isPresent()) {
            game = optionalGame.get();
        }

        return game;
    }
}
