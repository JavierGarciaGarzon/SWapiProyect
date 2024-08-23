package com.diverger.swapi.application.mapper;

import com.diverger.swapi.application.dto.FilmResponse;
import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.infrastructure.dto.FilmDto;
import com.diverger.swapi.infrastructure.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {
    public static Person toDomain(PersonDto dto) {
        return Person.builder()
                .name(dto.getName())
                .height(dto.getHeight())
                .mass(dto.getMass())
                .hairColor(dto.getHairColor())
                .eyeColor(dto.getEyeColor())
                .birthYear(dto.getBirthYear())
                .gender(dto.getGender())
                .homeworld(dto.getHomeworld())
                .vehicles(dto.getVehicles())
                .starships(dto.getStarships())
                .films(dto.getFilms())
                .species(dto.getSpecies())
                .created(dto.getCreated())
                .edited(dto.getEdited())
                .build();
    }



    public static PersonInfoResponse toResponse(Person person, String planetName, String fastestVehicleDriven, List<FilmDto> films) {
        List<FilmResponse> filmResponses = films.stream()
                .map(filmDto -> new FilmResponse(filmDto.getTitle(), filmDto.getReleaseDate()))
                .collect(Collectors.toList());

        return new PersonInfoResponse(
                person.getName(),
                person.getBirthYear(),
                person.getGender(),
                planetName,
                fastestVehicleDriven,
                filmResponses
        );
    }
}
