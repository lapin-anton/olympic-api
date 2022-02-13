package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import ru.lapinlisss.olympic_api.model.Result;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public void addResult(Result result) {
        resultRepository.save(result);
    }

}
