package com.diverger.swapi.infrastructure;

import com.diverger.swapi.infrastructure.client.SwapiClient;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SwapiClientTests {

    @InjectMocks
    private SwapiClient swapiClient;

    @Mock
    private RestTemplate restTemplate;

    public SwapiClientTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonByName() {
        PeopleSearch peopleSearch = new PeopleSearch();
        ResponseEntity<PeopleSearch> responseEntity = ResponseEntity.ok(peopleSearch);

        when(restTemplate.exchange(anyString(), any(), any(), eq(PeopleSearch.class)))
                .thenReturn(responseEntity);

        PeopleSearch result = swapiClient.getPersonByName("Leia", 1);
        assertNotNull(result);
    }
}