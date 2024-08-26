package com.diverger.swapi.domain.service;

import com.diverger.swapi.application.exception.PersonNotFoundException;
import com.diverger.swapi.application.mapper.FilmMapper;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.application.mapper.StarshipMapper;
import com.diverger.swapi.application.mapper.VehicleMapper;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.infrastructure.client.SwapiClient;
import com.diverger.swapi.infrastructure.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonDomainService {
    private final SwapiClient swapiClient;

    public PersonDomainService(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    // Method to find the fastest vehicle or starship driven by a person
    public String findFastestVehicleDriven(List<VehicleDto> vehicles, List<StarshipDto> starships) {
        return Stream.concat(vehicles.stream(), starships.stream())
                .max(Comparator.comparingInt(Transport::getMaxAtmospheringSpeed))
                .map(Transport::getName)
                .orElse("No vehicles or starships found");
    }

    public List<PersonDto> getPersonInfoByName(String name, Integer page) {
        List<Person> people = swapiClient.getPersonByName(name, page).getResults();
        if (people.isEmpty()) {
            throw new PersonNotFoundException("No person found with name: " + name);
        }
        List<PersonDto> peopleDto = new ArrayList<>();

        for (Person person : people) {
            String planetName = swapiClient.getPlanetByUrl(person.getHomeworld()).getName();

            List<VehicleDto> vehicleDtos = person.getVehicles().stream()
                    .map(swapiClient::getVehicleByUrl).map(VehicleMapper::toDto).
                    toList();

            List<StarshipDto> starshipDtos = person.getStarships().stream()
                    .map(swapiClient::getStarshipByUrl).map(StarshipMapper::toDto)
                    .toList();

            List<FilmDto> filmDtos = person.getFilms().stream()
                    .map(swapiClient::getFilmByUrl).map(FilmMapper::toDto)
                    .toList();

            PersonDto personDto = PersonMapper.toDto(person, vehicleDtos, starshipDtos, filmDtos);

            personDto.setFastestVehicleDriven(this.findFastestVehicleDriven(vehicleDtos, starshipDtos));
            personDto.setPlanetName(planetName);
            peopleDto.add(personDto);
        }
        return peopleDto;
    }
}
