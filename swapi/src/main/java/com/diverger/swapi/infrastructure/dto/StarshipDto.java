package com.diverger.swapi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StarshipDto {
    private String name;
    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
}
