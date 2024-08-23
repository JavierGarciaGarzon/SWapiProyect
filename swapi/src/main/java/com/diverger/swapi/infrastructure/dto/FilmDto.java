package com.diverger.swapi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FilmDto {
    private String title;
    @JsonProperty("release_date")
    private String releaseDate;
}
