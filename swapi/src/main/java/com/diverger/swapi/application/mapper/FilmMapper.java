package com.diverger.swapi.application.mapper;

import com.diverger.swapi.domain.model.Film;
import com.diverger.swapi.infrastructure.dto.FilmDto;

public class FilmMapper {

    public static FilmDto toDto(Film film) {
        return new FilmDto(film.getTitle(), film.getReleaseDate());
    }
}
