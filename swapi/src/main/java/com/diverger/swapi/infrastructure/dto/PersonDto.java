package com.diverger.swapi.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private String name;
    private String birthYear;
    private String gender;
    private int height;
    private String mass;
    private String fastestVehicleDriven;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private List<FilmDto> films;
    private List<VehicleDto> vehicles;
    private List<StarshipDto> starships;
    private List<String> species;
    private String homeworld;
    private String planetName;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

}