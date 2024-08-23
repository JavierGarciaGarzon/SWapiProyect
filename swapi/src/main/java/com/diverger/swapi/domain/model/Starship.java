package com.diverger.swapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Starship {
    private String name;
    private int maxAtmospheringSpeed;
}
