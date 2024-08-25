package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.service.PersonDomainService;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonApplicationService {

    private final PersonDomainService personDomainService;

    public PersonApplicationService(PersonDomainService personDomainService) {
        this.personDomainService = personDomainService;
    }

    public List<PersonInfoResponse> getPersonInfoByName(String name, Integer page) {
          return personDomainService.getPersonInfoByName(name, page).stream()
                  .map(PersonMapper::toResponse)
                  .toList();
    }
}
