package ru.lapinlisss.olympic_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDtoWithoutResults {

    private Long id;

    private CountryDto country;

    private String name;

    private String surname;

    private String gender;

    private String url;

}
