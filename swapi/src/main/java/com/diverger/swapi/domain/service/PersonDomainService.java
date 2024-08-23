package com.diverger.swapi.domain.service;

import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.domain.model.Starship;
import com.diverger.swapi.domain.model.Vehicle;
import com.diverger.swapi.infrastructure.dto.StarshipDto;
import com.diverger.swapi.infrastructure.dto.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonDomainService {

    // Method to find the fastest vehicle or starship driven by a person
    public String findFastestVehicleDriven(List<Vehicle> vehicles, List<Starship> starships) {
        Vehicle fastestVehicle = vehicles.stream()
                .max((v1, v2) -> Integer.compare(v1.getMaxAtmospheringSpeed(), v2.getMaxAtmospheringSpeed()))
                .orElse(null);

        Starship fastestStarship = starships.stream()
                .max((s1, s2) -> Integer.compare(s1.getMaxAtmospheringSpeed(), s2.getMaxAtmospheringSpeed()))
                .orElse(null);

        if (fastestVehicle != null && fastestStarship != null) {
            return fastestVehicle.getMaxAtmospheringSpeed() >= fastestStarship.getMaxAtmospheringSpeed()
                    ? fastestVehicle.getName()
                    : fastestStarship.getName();
        } else if (fastestVehicle != null) {
            return fastestVehicle.getName();
        } else if (fastestStarship != null) {
            return fastestStarship.getName();
        } else {
            return "No vehicles or starships found";
        }
    }

}
