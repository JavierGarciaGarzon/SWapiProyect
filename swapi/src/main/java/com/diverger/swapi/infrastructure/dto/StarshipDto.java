package com.diverger.swapi.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StarshipDto {
    private String name;
    private int maxAtmospheringSpeed;
}
