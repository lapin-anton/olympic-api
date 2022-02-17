package ru.lapinlisss.olympic_api.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CountryTeamRatingItem {

    private final String country;

    private final int gold;

    private final int silver;

    private final int bronze;

    private final int total;

}
