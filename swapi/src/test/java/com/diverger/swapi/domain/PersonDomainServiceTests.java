package com.diverger.swapi.domain;

import com.diverger.swapi.application.exception.PersonNotFoundException;
import com.diverger.swapi.domain.model.Person;
import com.diverger.swapi.domain.model.Planet;
import com.diverger.swapi.domain.model.Starship;
import com.diverger.swapi.domain.model.Vehicle;
import com.diverger.swapi.domain.service.PersonDomainService;
import com.diverger.swapi.infrastructure.client.SwapiClient;
import com.diverger.swapi.infrastructure.dto.PersonDto;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonDomainServiceTests {

    @InjectMocks
    private PersonDomainService personDomainService;

    @Mock
    private SwapiClient swapiClient;

    public PersonDomainServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonInfoByName() {
        // Crear un objeto Person simulado
        Person person = new Person();
        person.setHomeworld("http://swapi.dev/api/planets/1/"); // URL del planeta
        person.setVehicles(List.of("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/30/"));
        person.setStarships(List.of("https://swapi.dev/api/starships/12/", "https://swapi.dev/api/starships/22/"));

        // Crear objetos Vehicle y Starship simulados con diferentes velocidades
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
        person.setFilms(Collections.emptyList());

        // Configurar la búsqueda de personas
        PeopleSearch peopleSearch = new PeopleSearch();
        peopleSearch.setResults(Collections.singletonList(person));

        // Configurar el comportamiento simulado del cliente SwapiClient
        when(swapiClient.getPersonByName("Luke S", 1)).thenReturn(peopleSearch);
        when(swapiClient.getPlanetByUrl("http://swapi.dev/api/planets/1/")).thenReturn(planet);
        when(swapiClient.getVehicleByUrl("https://swapi.dev/api/vehicles/14/")).thenReturn(vehicle1);
        when(swapiClient.getVehicleByUrl("https://swapi.dev/api/vehicles/30/")).thenReturn(vehicle2);
        when(swapiClient.getStarshipByUrl("https://swapi.dev/api/starships/12/")).thenReturn(starship1);
        when(swapiClient.getStarshipByUrl("https://swapi.dev/api/starships/22/")).thenReturn(starship2);
        // Ejecutar la prueba
        List<PersonDto> result = personDomainService.getPersonInfoByName("Luke S", 1);

        // Validar los resultados
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Tatooine", result.get(0).getPlanetName());
        assertEquals("X-wing", result.get(0).getFastestVehicleDriven());
    }

    @Test
    void testGetPersonInfoByNamePersonNotFound() {
        // Crear un objeto PeopleSearch con una lista vacía
        PeopleSearch peopleSearch = new PeopleSearch();
        peopleSearch.setResults(Collections.emptyList());

        // Configurar el mock para devolver el objeto PeopleSearch con lista vacía
        when(swapiClient.getPersonByName("Unknown", 1)).thenReturn(peopleSearch);

        // Ejecutar y validar la excepción lanzada
        assertThrows(PersonNotFoundException.class, () -> {
            personDomainService.getPersonInfoByName("Unknown", 1);
        });
    }
}