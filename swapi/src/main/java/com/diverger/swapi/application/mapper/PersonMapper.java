package com.diverger.swapi.application.mapper;

import com.diverger.swapi.application.dto.FilmResponse;
import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.infrastructure.dto.FilmDto;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.infrastructure.dto.StarshipDto;
import com.diverger.swapi.infrastructure.dto.VehicleDto;

import java.util.List;

public class PersonMapper {
    public static PersonDto toDto(Person person, List<VehicleDto> vehicleDtos, List<StarshipDto> starshipDtos, List<FilmDto> filmDtos) {
        return PersonDto.builder()
                .name(person.getName())
                .height(person.getHeight())
                .mass(person.getMass())
                .hairColor(person.getHairColor())
                .eyeColor(person.getEyeColor())
                .birthYear(person.getBirthYear())
                .gender(person.getGender())
                .homeworld(person.getHomeworld())
                .vehicles(vehicleDtos)
                .starships(starshipDtos)
                .films(filmDtos)
                .created(person.getCreated())
                .edited(person.getEdited())
                .build();
    }


    public static PersonInfoResponse toResponse(PersonDto person) {
        List<FilmResponse> filmResponses = person.getFilms().stream()
                .map(filmDto -> new FilmResponse(filmDto.getTitle(), filmDto.getReleaseDate())).toList();

        return new PersonInfoResponse(
                person.getName(),
                person.getBirthYear(),
                person.getGender(),
                person.getPlanetName(),
                person.getFastestVehicleDriven(),
                filmResponses
        );
    }

}
