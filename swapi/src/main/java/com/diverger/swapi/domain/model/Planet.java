package com.diverger.swapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {
    private String name;
    private int rotationPeriod;
    private int orbitalPeriod;
    private int diameter;
    private String climate;
}
