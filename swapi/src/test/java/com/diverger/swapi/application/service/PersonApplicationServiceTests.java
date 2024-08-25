package com.diverger.swapi.application.service;

import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.service.PersonDomainService;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PersonApplicationServiceTests {

    @InjectMocks
    private PersonApplicationService personApplicationService;

    @Mock
    private PersonMapper personMapper;  // Mock para el PersonMapper

    @Mock
    private PersonDomainService personDomainService;

    @Test
    public void testGetPersonInfoByName() {
        PersonDto personDto = new PersonDto();
        personDto.setFilms(Collections.emptyList());
        personDto.setVehicles(Collections.emptyList());
        personDto.setStarships(Collections.emptyList());

        when(personDomainService.getPersonInfoByName("Luke S", 1))
                .thenReturn(Collections.singletonList(personDto));

        PersonInfoResponse personInfoResponse = new PersonInfoResponse();
        when(personMapper.toResponse(personDto)).thenReturn(personInfoResponse);

        List<PersonInfoResponse> result = personApplicationService.getPersonInfoByName("Luke S", 1);
        assertEquals(1, result.size());
        assertEquals(personInfoResponse, result.get(0));
    }
}
