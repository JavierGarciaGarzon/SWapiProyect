package com.diverger.swapi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class PersonDto {
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    private String gender;
    private int height;
    private String mass;
    @JsonProperty("hair_color")
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private List<String> films;
    private List<String> vehicles;
    private List<String> starships;
    private List<String> species;
    private String homeworld;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

}