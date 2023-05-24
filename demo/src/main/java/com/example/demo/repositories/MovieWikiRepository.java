package com.example.demo.repositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import com.example.demo.models.MovieWiki;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Repository
@Slf4j
public class MovieWikiRepository {
    @Autowired
    private ElasticsearchClient ELASTICSEARCH_CLIENT = null;

    private final String indexName = "moviewiki";

    public MovieWiki getMovieByName(String MovieName){
        SearchResponse<MovieWiki> MovieResponse = null;
        try {
            MovieResponse = this.ELASTICSEARCH_CLIENT.search(s -> s
                            .index(this.indexName)
                            .size(10)
                            .query(q -> q
                                    .match(t -> t
                                            .field("name")
                                            .query(MovieName))
                            ),
                    MovieWiki.class
            );
        } catch (IOException e) {
            log.error("Exception while searching profile", e);
        }

        MovieWiki returnedMovie =  new MovieWiki();
        if ((MovieResponse == null) || (CollectionUtils.isEmpty(MovieResponse.hits().hits()))) {
            return new MovieWiki();
        }


        returnedMovie = MovieResponse.hits().hits().get(0).source();
        return returnedMovie;
    }

    public MovieWiki getMovieById(String id){

        MovieWiki searchedMovie = new MovieWiki();
        GetRequest getRequest = new GetRequest.Builder()
                .index(this.indexName)
                .id(id)
                .build();
        try {
            GetResponse<MovieWiki> response = this.ELASTICSEARCH_CLIENT.get(getRequest, MovieWiki.class);

            if ((response == null) || (response.source() == null)) {
                return null;
            }

            searchedMovie = response.source();


        }
        catch (IOException exception) {
            log.error("Exception while searching by Id");
        }

        return searchedMovie;
    }

    public IndexResponse createMovie() throws IOException {
        MovieWiki movie = new MovieWiki("1 Day", "https://upload.wikimedia.org/wikipedia/en/c/c7/1_Day_film.jpg", "Flash (Dylan Duffus) receives a phone call from Angel");
        IndexRequest<MovieWiki> request = IndexRequest.of(i -> i
                .index("moviewiki")
                .document(movie)
        );
        co.elastic.clients.elasticsearch.core.IndexResponse response = this.ELASTICSEARCH_CLIENT.index(request);
        return response;

    }

    public MovieWiki updateMovieById(String id, MovieWiki newMovie) throws IOException {

        MovieWiki movieToUpdate = this.getMovieById(id);

        movieToUpdate.update(newMovie);

        UpdateRequest updateRequest = new UpdateRequest.Builder<>()
                .id(id)
                .index(this.indexName)
                .doc(movieToUpdate)
                .build();

        this.ELASTICSEARCH_CLIENT.update(updateRequest, MovieWiki.class);


        return movieToUpdate;
    }

    public Boolean deleteMovieById(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index(this.indexName)
                .id(id)
                .build();

        this.ELASTICSEARCH_CLIENT.delete(deleteRequest);
        return true;
    }
}