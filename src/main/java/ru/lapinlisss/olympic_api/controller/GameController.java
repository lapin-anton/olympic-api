package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lapinlisss.olympic_api.mapper.CustomMapper;
import ru.lapinlisss.olympic_api.model.dto.GameDto;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.repository.GameRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("game")
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;

    // get all
    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<GameDto>> getAllGames() {
        log.info("Income request to get all games");
        List<Game> games = gameRepository.findAll();

        games.sort(Comparator.comparingInt(Game::getYear));

        Collections.reverse(games);

        List<GameDto> gameDtos = games.stream()
                .map(CustomMapper.INSTANCE::mapGameToGameDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(gameDtos);
    }

    // get by id
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        log.info("Income request to get game by id {}", id);
        Optional<Game> gameOptional = gameRepository.findById(id);
        Game game = null;
        if (gameOptional.isPresent()) {
            game = gameOptional.get();
        }
        return ResponseEntity.ok().body(CustomMapper.INSTANCE.mapGameToGameDto(game));
    }

}
