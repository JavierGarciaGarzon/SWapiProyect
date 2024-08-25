package com.diverger.swapi.application.mapper;

import com.diverger.swapi.domain.model.Vehicle;
import com.diverger.swapi.infrastructure.dto.VehicleDto;

public class VehicleMapper {

    public static Vehicle toDomain(VehicleDto dto) {
        return new Vehicle(dto.getName(), String.valueOf(dto.getMaxAtmospheringSpeed()));
    }

    public static VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getName(), Integer.parseInt(vehicle.getMaxAtmospheringSpeed()));
    }
}
