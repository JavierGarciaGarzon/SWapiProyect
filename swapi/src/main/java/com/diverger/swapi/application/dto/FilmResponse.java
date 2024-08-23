package com.diverger.swapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmResponse {
    private String name;
    private String releaseDate;
}
