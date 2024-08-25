package com.diverger.swapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String name;
    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
}
