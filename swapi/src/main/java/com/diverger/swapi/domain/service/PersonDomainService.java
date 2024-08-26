package com.diverger.swapi.domain.service;

import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.infrastructure.dto.StarshipDto;
import com.diverger.swapi.infrastructure.dto.VehicleDto;

import java.util.List;

public interface PersonDomainService {

    // Method to find the fastest vehicle or starship driven by a person
    String findFastestVehicleDriven(List<VehicleDto> vehicles, List<StarshipDto> starships);

    List<PersonDto> getPersonInfoByName(String name, Integer page);
}
