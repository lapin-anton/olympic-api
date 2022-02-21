package ru.lapinlisss.olympic_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportDto {

    private Long id;

    private String name;

    private String thumbnailUrl;

}
