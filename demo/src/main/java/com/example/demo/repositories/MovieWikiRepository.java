package com.example.demo.repositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.demo.models.MovieWiki;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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
        if (MovieResponse == null) {
            return new MovieWiki();
        }

        if (CollectionUtils.isEmpty(MovieResponse.hits().hits())) {
            return new MovieWiki();
        }

        returnedMovie = MovieResponse.hits().hits().get(0).source();
        return returnedMovie;
    }
}