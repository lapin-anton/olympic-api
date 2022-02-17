package ru.lapinlisss.olympic_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lapinlisss.olympic_api.model.dto.*;
import ru.lapinlisss.olympic_api.model.entity.*;

@Mapper
public interface CustomMapper {

    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    SportDto mapSportToSportDto(Sport sport);
    
    GameDto mapGameToGameDto(Game game);
    
    CountryDto mapCountryToCountryDto(Country country);

    AthleteDtoWithoutResults mapAthleteToAthleteDtoWithoutResults(Athlete athlete);

    ResultDto mapResultToResultDto(Result result);

    AthleteDto mapAthleteToAthleteDto(Athlete athlete);

}