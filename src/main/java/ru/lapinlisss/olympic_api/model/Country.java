package ru.lapinlisss.olympic_api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String trigger;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<Athlete> athletes;

}
