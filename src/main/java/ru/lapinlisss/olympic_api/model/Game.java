package ru.lapinlisss.olympic_api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Integer year;

    @OneToMany(mappedBy = "game")
    private List<Result> results;
}
