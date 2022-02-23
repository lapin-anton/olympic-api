package ru.lapinlisss.olympic_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long id;

    private String type;

    private Integer year;

    private String city;

    private String thumbnailUrl;

    private String description;

}
