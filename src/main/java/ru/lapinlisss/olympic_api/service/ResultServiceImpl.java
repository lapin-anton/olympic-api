package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public List<Result> getResultsByGame(Game game) {
        return resultRepository.findAllByGame(game);
    }
}
