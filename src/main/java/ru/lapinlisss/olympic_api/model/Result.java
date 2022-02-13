package ru.lapinlisss.olympic_api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Column(name = "athlete_rank")
    private Integer athleteRank;

    @Column(name = "athlete_age")
    private Integer athleteAge;

    private Integer gold;

    private Integer silver;

    private Integer bronze;

    private Integer total;

}
