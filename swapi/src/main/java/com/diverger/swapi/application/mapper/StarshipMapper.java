package com.diverger.swapi.application.mapper;

import com.diverger.swapi.domain.model.Starship;
import com.diverger.swapi.infrastructure.dto.StarshipDto;

public class StarshipMapper {

    public static Starship toDomain(StarshipDto dto) {
        return new Starship(dto.getName(), String.valueOf(dto.getMaxAtmospheringSpeed()));
    }
    public static StarshipDto toDto(Starship starship) {
        return new StarshipDto(starship.getName(), Integer.parseInt(starship.getMaxAtmospheringSpeed()));
    }
}
