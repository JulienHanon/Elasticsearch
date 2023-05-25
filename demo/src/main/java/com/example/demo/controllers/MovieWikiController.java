package com.example.demo.controllers;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.example.demo.models.MovieWiki;
import com.example.demo.repositories.MovieWikiRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieWikiController {

    private final MovieWikiRepository movieWikiRepository;

    public MovieWikiController(MovieWikiRepository movieWikiRepository) {
        this.movieWikiRepository = movieWikiRepository;
    }

    @GetMapping("api/v1/getMovieName/{name}")
    public List<MovieWiki> getMovieByName(@PathVariable String name) {
        return this.movieWikiRepository.getMovieByName(name);
    }

    @GetMapping("api/v1/getMovieId/{id}")
    public MovieWiki getMovieById(@PathVariable String id) {
        return this.movieWikiRepository.getMovieById(id);
    }

    @PutMapping ("api/v1/createMovie")
    public IndexResponse createMovie() throws IOException {
        return this.movieWikiRepository.createMovie();
    }

    @PutMapping("api/v1/updateMovie/{id}")
    public MovieWiki updateMovieById(@PathVariable String id, @RequestBody MovieWiki newMovie) throws IOException {
        return this.movieWikiRepository.updateMovieById(id, newMovie);
    }

    @DeleteMapping("api/v1/deleteMovie/{id}")
    public Boolean deleteMovieById(@PathVariable String id) throws IOException {
        return this.movieWikiRepository.deleteMovieById(id);
    }

}
