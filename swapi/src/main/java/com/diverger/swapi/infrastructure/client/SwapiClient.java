package com.diverger.swapi.infrastructure.client;

import com.diverger.swapi.application.exception.SwapiException;
import com.diverger.swapi.domain.model.Film;
import com.diverger.swapi.domain.model.Planet;
import com.diverger.swapi.domain.model.Starship;
import com.diverger.swapi.domain.model.Vehicle;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;


@Service
public class SwapiClient {
    private final RestTemplate restTemplate;

    @Value("${swapi.base-url}")
    private final String swapiBaseUrl;

    @Value("${swapi.search-url}")
    private final String searchUrl;

    public SwapiClient(RestTemplate restTemplate,
                       @Value("${swapi.base-url}") String swapiBaseUrl, @Value("${swapi.search-url}") String searchUrl) {
        this.restTemplate = restTemplate;
        this.swapiBaseUrl = swapiBaseUrl;
        this.searchUrl = searchUrl;
    }

    @Cacheable("people")
    public PeopleSearch getPersonByName(String name, Integer page) {
        String url = String.format("%s%s%s", swapiBaseUrl, searchUrl, name);
        if (page != null){
            url= String.format("%s&page=%d", url, page);
        }
        try {
            ResponseEntity<PeopleSearch> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    PeopleSearch.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new SwapiException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Planet getPlanetByUrl(String url) {
        String finalUrl = String.format(url, swapiBaseUrl);
        return restTemplate.getForObject(finalUrl, Planet.class);
    }

    public Film getFilmByUrl(String url) {
        return restTemplate.getForObject(url, Film.class);
    }

    public Starship getStarshipByUrl(String url) {
        return restTemplate.getForObject(url, Starship.class);
    }

    public Vehicle getVehicleByUrl(String url) {
        return restTemplate.getForObject(url, Vehicle.class);
    }
}
