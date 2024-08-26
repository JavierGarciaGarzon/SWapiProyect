package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.exception.PersonNotFoundException;

import java.util.List;

public interface PersonApplicationService {
    /**
     * Retrieves detailed information about a person by their name and page number.
     * This method interacts with the PersonDomainService to fetch and map the data.
     *
     * @param name the name of the person to retrieve
     * @param page the page number of the SWAPI results to search
     * @return a list of PersonInfoResponse objects containing the person's details
     * @throws PersonNotFoundException if no person is found with the given name
     */
    List<PersonInfoResponse> getPersonInfoByName(String name, Integer page);
}
