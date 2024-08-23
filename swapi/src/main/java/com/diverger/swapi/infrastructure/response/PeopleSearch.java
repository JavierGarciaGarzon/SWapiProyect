package com.diverger.swapi.infrastructure.response;

import com.diverger.swapi.infrastructure.dto.PersonDto;
import lombok.Data;

import java.util.List;

@Data
public class PeopleSearch {
    private int count;
    private List<PersonDto> results;

}
