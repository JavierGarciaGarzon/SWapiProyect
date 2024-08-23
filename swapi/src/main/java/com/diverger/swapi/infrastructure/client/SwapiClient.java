package com.diverger.swapi.infrastructure.client;

import com.diverger.swapi.infrastructure.dto.FilmDto;
import com.diverger.swapi.infrastructure.dto.PlanetDto;
import com.diverger.swapi.infrastructure.dto.StarshipDto;
import com.diverger.swapi.infrastructure.dto.VehicleDto;
import com.diverger.swapi.infrastructure.response.PeopleSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;


@Service
public class SwapiClient {
    private final RestTemplate restTemplate;

    @Value("${swapi.base-url}")
    private final String swapiBaseUrl;

    public SwapiClient(RestTemplate restTemplate,
                       @Value("${swapi.base-url}") String swapiBaseUrl) {
        this.restTemplate = restTemplate;
        this.swapiBaseUrl = swapiBaseUrl;
    }

    @Cacheable("people")
    public PeopleSearch getPersonByName(String name) {
        String url = String.format("%s/people/?search=%s", swapiBaseUrl, name);
        try {
            ResponseEntity<PeopleSearch> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    PeopleSearch.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    public PlanetDto getPlanetByUrl(String url) {
        String finalUrl = String.format(url, swapiBaseUrl);
        return restTemplate.getForObject(finalUrl, PlanetDto.class);
    }

    public FilmDto getFilmByUrl(String url) {
        return restTemplate.getForObject(url, FilmDto.class);
    }

    public StarshipDto getStarshipByUrl(String url) {
        return restTemplate.getForObject(url, StarshipDto.class);
    }

    public VehicleDto getVehicleByUrl(String url) {
        return restTemplate.getForObject(url, VehicleDto.class);
    }
}
