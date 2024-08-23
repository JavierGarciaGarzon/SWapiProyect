package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.application.mapper.StarshipMapper;
import com.diverger.swapi.application.mapper.VehicleMapper;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.domain.model.Starship;
import com.diverger.swapi.domain.model.Vehicle;
import com.diverger.swapi.domain.service.PersonDomainService;
import com.diverger.swapi.infrastructure.client.SwapiClient;
import com.diverger.swapi.infrastructure.dto.*;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonApplicationService {
    private final SwapiClient swapiClient;
    private final PersonDomainService personDomainService;

    public PersonApplicationService(SwapiClient swapiClient, PersonDomainService personDomainService) {
        this.swapiClient = swapiClient;
        this.personDomainService = personDomainService;
    }

    public PeopleSearch findByName(String name) {
        return swapiClient.getPersonByName(name);
    }

    public List<PersonInfoResponse> getPersonInfoByName(String name) {
        List<PersonDto> people = swapiClient.getPersonByName(name).getResults();
        List<PersonInfoResponse> personInfoResponses = new ArrayList<>();
        for (PersonDto personDto : people) {
            Person person = PersonMapper.toDomain(personDto);

            PlanetDto planetDto = swapiClient.getPlanetByUrl(personDto.getHomeworld());

            List<VehicleDto> vehicleDtos = personDto.getVehicles().stream()
                    .map(swapiClient::getVehicleByUrl)
                    .toList();

            List<Vehicle> vehicles = vehicleDtos.stream()
                    .map(VehicleMapper::toDomain)
                    .collect(Collectors.toList());

            List<StarshipDto> starshipDtos = personDto.getStarships().stream()
                    .map(swapiClient::getStarshipByUrl)
                    .toList();

            List<Starship> starships = starshipDtos.stream()
                    .map(StarshipMapper::toDomain)
                    .collect(Collectors.toList());

            String fastestVehicleDriven = personDomainService.findFastestVehicleDriven(vehicles, starships);

            List<FilmDto> filmDtos = personDto.getFilms().stream()
                    .map(swapiClient::getFilmByUrl)
                    .collect(Collectors.toList());
            PersonInfoResponse personInfoResponse = PersonMapper.toResponse(person, planetDto.getName(), fastestVehicleDriven, filmDtos);
            personInfoResponses.add(personInfoResponse);
        }
        return personInfoResponses;
    }
}
