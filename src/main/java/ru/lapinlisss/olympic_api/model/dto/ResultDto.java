package ru.lapinlisss.olympic_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lapinlisss.olympic_api.model.entity.Athlete;
import ru.lapinlisss.olympic_api.model.entity.Game;
import ru.lapinlisss.olympic_api.model.entity.Sport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

    private Long id;

    private GameDto game;

    private SportDto sport;

    private Integer athleteRank;

    private Integer athleteAge;

    private Integer gold;

    private Integer silver;

    private Integer bronze;

    private Integer total;

}
