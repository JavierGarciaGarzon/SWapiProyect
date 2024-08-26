package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.service.PersonDomainService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonApplicationServiceImpl implements PersonApplicationService {

    private final PersonDomainService personDomainService;
    private final PersonMapper personMapper;

    public PersonApplicationServiceImpl(PersonDomainService personDomainService, PersonMapper personMapper) {
        this.personDomainService = personDomainService;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonInfoResponse> getPersonInfoByName(String name, Integer page) {
        return personDomainService.getPersonInfoByName(name, page).stream()
                .map(personMapper::toResponse)
                .toList();
    }
}
