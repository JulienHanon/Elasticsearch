package com.example.demo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import lombok.extern.slf4j.Slf4j;
import javax.net.ssl.SSLContext;
import java.io.IOException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.demo.repositories")
@Slf4j
public class MainConfig {

    private final String login = "elastic";
    private String password = "ex1XmuagPRBG6J*CfMC=";

    @Bean
    public RestClient getRestClient() {
        String fingerPrint = "b6dc430682971970e18bc02b70614c5f1440bc7c24b5aa59e21f30da61cb2486";
        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerPrint);


        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(login, password)
        );

        RestClient restClient = RestClient.builder(
                        new HttpHost("localhost", 9200, "https"))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv))
                .build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() throws IOException {
        return new RestClientTransport(
                getRestClient(), new JacksonJsonpMapper());
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient() throws IOException {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }

}