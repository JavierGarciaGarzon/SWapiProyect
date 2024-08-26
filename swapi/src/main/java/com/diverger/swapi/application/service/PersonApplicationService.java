package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.exception.PersonNotFoundException;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.service.PersonDomainService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonApplicationService {

    private final PersonDomainService personDomainService;
    private final PersonMapper personMapper;

    public PersonApplicationService(PersonDomainService personDomainService, PersonMapper personMapper) {
        this.personDomainService = personDomainService;
        this.personMapper = personMapper;
    }

    /**
     * Retrieves detailed information about a person by their name and page number.
     * This method interacts with the PersonDomainService to fetch and map the data.
     *
     * @param name the name of the person to retrieve
     * @param page the page number of the SWAPI results to search
     * @return a list of PersonInfoResponse objects containing the person's details
     * @throws PersonNotFoundException if no person is found with the given name
     */
    public List<PersonInfoResponse> getPersonInfoByName(String name, Integer page) {
        return personDomainService.getPersonInfoByName(name, page).stream()
                .map(personMapper::toResponse)
                .toList();
    }
}
