package com.example.demo.controllers;

import com.example.demo.models.MovieWiki;
import com.example.demo.repositories.MovieWikiRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieWikiController {

    private final MovieWikiRepository movieWikiRepository;

    public MovieWikiController(MovieWikiRepository movieWikiRepository) {
        this.movieWikiRepository = movieWikiRepository;
    }

    @GetMapping("api/v1/getMovie/{name}")
    public MovieWiki getMovieByName(@PathVariable String name) {
        return this.movieWikiRepository.getMovieByName(name);
    }
}
