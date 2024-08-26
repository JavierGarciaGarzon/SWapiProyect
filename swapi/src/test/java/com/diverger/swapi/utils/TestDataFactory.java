package com.diverger.swapi.utils;

import com.diverger.swapi.application.dto.FilmResponse;
import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.infrastructure.dto.FilmDto;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.infrastructure.dto.StarshipDto;
import com.diverger.swapi.infrastructure.dto.VehicleDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDataFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Person createPersonFromJson(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return objectMapper.readValue(json, Person.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Person from JSON", e);
        }
    }


    public static PersonDto createPersonDtoFromJson(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Person person = objectMapper.readValue(json, Person.class);

            List<VehicleDto> vehicleDtos = new ArrayList<>();

            VehicleDto vehicle1 = new VehicleDto();
            vehicle1.setName("Snowspeeder");
            vehicle1.setMaxAtmospheringSpeed(650);

            VehicleDto vehicle2 = new VehicleDto();
            vehicle2.setName("Imperial Speeder Bike");
            vehicle2.setMaxAtmospheringSpeed(360);

            vehicleDtos.add(vehicle1);
            vehicleDtos.add(vehicle2);

            List<StarshipDto> starshipDtos = new ArrayList<>();
            StarshipDto starship1 = new StarshipDto();
            starship1.setName("X-wing");
            starship1.setMaxAtmospheringSpeed(1050);

            StarshipDto starship2 = new StarshipDto();
            starship2.setName("Imperial shuttle");
            starship2.setMaxAtmospheringSpeed(850);

            starshipDtos.add(starship1);
            starshipDtos.add(starship2);

            List<FilmDto> filmDtos = Collections.singletonList(new FilmDto("A New Hope", "1977-05-25"));

            PersonDto personDto = PersonMapper.toDto(person, vehicleDtos, starshipDtos, filmDtos);
            personDto.setFastestVehicleDriven("X-wing");
            personDto.setPlanetName("Tatooine");
            return personDto;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Person from JSON", e);
        }
    }

    public static PersonDto createPersonDto() {
        PersonDto personDto = new PersonDto();
        personDto.setName("Luke Skywalker");
        personDto.setHomeworld("https://swapi.dev/api/planets/1/");
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        VehicleDto vehicle1 = new VehicleDto();
        vehicle1.setName("Snowspeeder");
        vehicle1.setMaxAtmospheringSpeed(650);

        VehicleDto vehicle2 = new VehicleDto();
        vehicle2.setName("Imperial Speeder Bike");
        vehicle2.setMaxAtmospheringSpeed(360);

        vehicleDtos.add(vehicle1);
        vehicleDtos.add(vehicle2);

        StarshipDto starship1 = new StarshipDto();
        starship1.setName("X-wing");
        starship1.setMaxAtmospheringSpeed(1050);

        StarshipDto starship2 = new StarshipDto();
        starship2.setName("Imperial shuttle");
        starship2.setMaxAtmospheringSpeed(850);

        return personDto;
    }

    public static PersonInfoResponse createPersonInfoResponseFromDto(PersonDto personDto) {
        PersonInfoResponse personInfoResponse = new PersonInfoResponse();
        personInfoResponse.setName(personDto.getName());
        personInfoResponse.setBirthYear(personDto.getBirthYear());
        personInfoResponse.setGender(personDto.getGender());
        personInfoResponse.setFastestVehicleDriven(personDto.getFastestVehicleDriven());
        personInfoResponse.setPlanetName(personDto.getPlanetName());
        personInfoResponse.setFilms(Collections.singletonList(new FilmResponse("A New Hope", "1977-05-25")));
        return personInfoResponse;
    }
}
