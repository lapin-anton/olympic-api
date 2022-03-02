package ru.lapinlisss.olympic_api.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "game_athlete_count_vw")
public class GameAthletesCount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long gameId;

    @Column(name="athletes")
    private Long athleteCount;

}
