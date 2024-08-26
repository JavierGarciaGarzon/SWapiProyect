package com.diverger.swapi.application.service;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.mapper.PersonMapper;
import com.diverger.swapi.domain.service.PersonDomainServiceImpl;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonApplicationServiceTests {

    @InjectMocks
    private PersonApplicationServiceImpl personApplicationService;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonDomainServiceImpl personDomainService;

    @Test
    void testGetPersonInfoByName() {
        // Setting up mock data for Luke Skywalker
        PersonDto personDto = TestDataFactory.createPersonDtoFromJson("src/test/resources/testdata/PersonLuke.json");

        when(personDomainService.getPersonInfoByName("Luke Skywalker", 1))
                .thenReturn(Collections.singletonList(personDto));

        PersonInfoResponse personInfoResponse = TestDataFactory.createPersonInfoResponseFromDto(personDto);
        when(personMapper.toResponse(personDto)).thenReturn(personInfoResponse);

        List<PersonInfoResponse> result = personApplicationService.getPersonInfoByName("Luke Skywalker", 1);

        assertEquals(1, result.size());
        assertEquals("Luke Skywalker", result.get(0).getName());
        assertEquals("19BBY", result.get(0).getBirthYear());
        assertEquals("male", result.get(0).getGender());
        assertEquals("X-wing", result.get(0).getFastestVehicleDriven());
        assertEquals("Tatooine", result.get(0).getPlanetName());
        assertEquals("A New Hope", result.get(0).getFilms().get(0).getName());
    }
}
