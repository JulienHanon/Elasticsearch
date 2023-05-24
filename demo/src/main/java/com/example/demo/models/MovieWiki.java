package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.thymeleaf.util.StringUtils;

import javax.naming.Name;

@Document(indexName = "moviewiki")
public class MovieWiki {

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("plot")
    private String plot;

    public MovieWiki() {
    }

    public MovieWiki(String name, String image, String plot) {
        this.name = name;
        this.image = image;
        this.plot = plot;
    }


    public MovieWiki update(MovieWiki movieToUpdate) {
        if (!StringUtils.isEmpty(movieToUpdate.getMovieName())){
            this.setMovieName(movieToUpdate.getMovieName());
        }
        return this;

    }

    private void setMovieName(String movieName) {
        this.name = movieName;
    }

    private String getMovieName() {
        return name;
    }
}

