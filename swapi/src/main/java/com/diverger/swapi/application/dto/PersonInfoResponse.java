package com.diverger.swapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoResponse{
    private String name;
    private String birthYear;
    private String gender;
    private String planetName;
    private String fastestVehicleDriven;
    private List<FilmResponse> films;
}
