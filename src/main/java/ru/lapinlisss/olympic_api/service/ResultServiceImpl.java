package ru.lapinlisss.olympic_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.ResultDto;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Result;
import ru.lapinlisss.olympic_api.model.response.CountryTeamRatingItem;
import ru.lapinlisss.olympic_api.repository.GameRepository;
import ru.lapinlisss.olympic_api.repository.ResultRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    @Override
    public List<CountryTeamRatingItem> getCountryTeamRatingByGame(String type, int year) {
        List<CountryTeamRatingItem> rating = new ArrayList<>();

        List<ResultDto> resultDtos = getAllResultsByGame(type, year);

        Map<String, List<ResultDto>> resultsByCountry = new HashMap<>();

        resultDtos.forEach(res -> {
            List<ResultDto> results = new ArrayList<>();
            if (resultsByCountry.containsKey(res.getAthlete().getCountry().getName())) {
                results = resultsByCountry.get(res.getAthlete().getCountry().getName());
            }
            results.add(res);
            resultsByCountry.put(res.getAthlete().getCountry().getName(), results);
        });

        for (Map.Entry<String, List<ResultDto>> pair: resultsByCountry.entrySet()) {
            AtomicInteger gold = new AtomicInteger(0);
            AtomicInteger silver = new AtomicInteger(0);
            AtomicInteger bronze = new AtomicInteger(0);
            AtomicInteger total = new AtomicInteger(0);
            pair.getValue().forEach(r -> {
                gold.addAndGet(r.getGold());
                silver.addAndGet(r.getSilver());
                bronze.addAndGet(r.getBronze());
                total.addAndGet(r.getTotal());
            });

            rating.add(CountryTeamRatingItem.builder()
                    .country(pair.getKey())
                    .gold(gold.get())
                    .silver(silver.get())
                    .bronze(bronze.get())
                    .total(total.get())
                    .build());
        }

        rating.sort(Comparator.comparing(CountryTeamRatingItem::getGold)
                .thenComparing(CountryTeamRatingItem::getSilver)
                .thenComparing(CountryTeamRatingItem::getBronze));

        Collections.reverse(rating);

        return rating;
    }

    private List<ResultDto> getAllResultsByGame(String type, int year) {

        List<Result> results = new ArrayList<>();

        Optional<Game> gameOptional = gameRepository.findGameByTypeAndYear(type, year);

        if (gameOptional.isPresent()) {
            results = resultRepository.findAllByGame(gameOptional.get());
        }

        return results.stream()
                .map(CustomMapper.INSTANCE::mapResultToResultDto)
                .collect(Collectors.toList());
    }
}
