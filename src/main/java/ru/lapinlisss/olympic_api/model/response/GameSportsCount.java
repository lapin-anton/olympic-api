package ru.lapinlisss.olympic_api.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "game_sport_count_vw")
public class GameSportsCount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long gameId;

    @Column(name="sports")
    private Long sportCount;

}
