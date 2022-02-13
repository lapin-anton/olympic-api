package ru.lapinlisss.olympic_api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String name;

    private String surname;

    private String gender;

    private String url;

    @OneToMany(mappedBy = "sport")
    private List<Result> results;

}
