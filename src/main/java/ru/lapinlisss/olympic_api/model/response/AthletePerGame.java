package ru.lapinlisss.olympic_api.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "athletes_per_game_vw")
public class AthletePerGame {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="a_name")
    private String name;

    @Column(name="a_surname")
    private String surname;

    @Column(name="a_gender")
    private String gender;

    @Column(name="url")
    private String url;

    @Column(name="a_age")
    private Integer age;

    @Column(name="team_rank")
    private Integer teamRank;

    @Column(name="country")
    private String country;

    @Column(name="sport")
    private String sport;

    @Column(name="game_id")
    private long gameId;

    @Column(name="gold")
    private int gold;

    @Column(name="silver")
    private int silver;

    @Column(name="bronze")
    private int bronze;

    @Column(name="total")
    private int total;

}
