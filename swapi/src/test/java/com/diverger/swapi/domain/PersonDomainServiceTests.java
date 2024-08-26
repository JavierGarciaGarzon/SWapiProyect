package com.diverger.swapi.domain;

import com.diverger.swapi.application.exception.PersonNotFoundException;
import com.diverger.swapi.domain.model.*;
import com.diverger.swapi.domain.service.PersonDomainServiceImpl;
import com.diverger.swapi.infrastructure.client.SwapiClient;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import com.diverger.swapi.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonDomainServiceTests {

    @InjectMocks
    private PersonDomainServiceImpl personDomainService;

    @Mock
    private SwapiClient swapiClient;

    public PersonDomainServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonInfoByName() {
        Person person = TestDataFactory.createPersonFromJson("src/test/resources/testdata/PersonLuke.json");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Snowspeeder");
        vehicle1.setMaxAtmospheringSpeed("650");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Imperial Speeder Bike");
        vehicle2.setMaxAtmospheringSpeed("360");

        Starship starship1 = new Starship();
        starship1.setName("X-wing");
        starship1.setMaxAtmospheringSpeed("1050");

        Starship starship2 = new Starship();
        starship2.setName("Imperial shuttle");
        starship2.setMaxAtmospheringSpeed("850");

        Planet planet = new Planet();
        planet.setName("Tatooine");

        Film film = new Film();
        film.setTitle("A New Hope");
        film.setEpisodeId(4);
        film.setReleaseDate("1977-05-25");

        PeopleSearch peopleSearch = new PeopleSearch();
        peopleSearch.setResults(Collections.singletonList(person));

        when(swapiClient.getPersonByName("Luke Skywalker", 1)).thenReturn(peopleSearch);
        when(swapiClient.getPlanetByUrl(person.getHomeworld())).thenReturn(planet);
        when(swapiClient.getVehicleByUrl(person.getVehicles().get(0))).thenReturn(vehicle1);
        when(swapiClient.getVehicleByUrl(person.getVehicles().get(1))).thenReturn(vehicle2);
        when(swapiClient.getStarshipByUrl(person.getStarships().get(0))).thenReturn(starship1);
        when(swapiClient.getStarshipByUrl(person.getStarships().get(1))).thenReturn(starship2);
        when(swapiClient.getFilmByUrl(person.getFilms().get(0))).thenReturn(film);

        List<PersonDto> result = personDomainService.getPersonInfoByName("Luke Skywalker", 1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Luke Skywalker", result.get(0).getName());
        assertEquals("Tatooine", result.get(0).getPlanetName());
        assertEquals("X-wing", result.get(0).getFastestVehicleDriven());
        assertEquals("A New Hope", result.get(0).getFilms().get(0).getTitle());
    }

    @Test
    void testGetPersonInfoByNamePersonNotFound() {
        PeopleSearch peopleSearch = new PeopleSearch();
        peopleSearch.setResults(Collections.emptyList());

        when(swapiClient.getPersonByName("UnknownERROR", 1)).thenReturn(peopleSearch);

        assertThrows(PersonNotFoundException.class, () -> {
            personDomainService.getPersonInfoByName("UnknownERROR", 1);
        });
    }
}