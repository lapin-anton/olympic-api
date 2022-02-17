package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.repository.GameRepository;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    private final GameRepository gameRepository;

    @Override
    public Page<Result> getResultsByGame(Game game, Pageable pageable) {
        return resultRepository.findAllByGame(game, pageable);
    }

    @Override
    public List<Result> getAllResultsByGame(String type, int year, int page) {

        List<Result> results = new ArrayList<>();

        Optional<Game> gameOptional = gameRepository.findGameByTypeAndYear(type, year);

        if (gameOptional.isPresent()) {
            results = resultRepository.findAllByGame(gameOptional.get(), PageRequest.of(page, 100)).toList();
        }

        return results;
    }
}
