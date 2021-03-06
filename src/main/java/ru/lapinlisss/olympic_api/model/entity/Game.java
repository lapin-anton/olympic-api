package ru.lapinlisss.olympic_api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Integer year;

    private String city;

    @Column(name="thumbnail_url")
    private String thumbnailUrl;

    private String description;

    @OneToMany(mappedBy = "game")
    private List<Result> results;
}
