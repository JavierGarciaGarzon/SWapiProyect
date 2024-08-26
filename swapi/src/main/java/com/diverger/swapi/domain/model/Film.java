package com.diverger.swapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private String title;
    private int episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;
}
