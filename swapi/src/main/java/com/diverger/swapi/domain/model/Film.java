package com.diverger.swapi.domain.model;

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
    private String releaseDate;
}
