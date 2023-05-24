package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "moviewiki")
public class MovieWiki {

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("plot")
    private String plot;







}

