package com.diverger.swapi.infrastructure.response;

import com.diverger.swapi.domain.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class PeopleSearch {
    private int count;
    private List<Person> results;

}
