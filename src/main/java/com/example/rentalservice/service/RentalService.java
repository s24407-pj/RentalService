package com.example.rentalservice.service;

import com.example.rentalservice.model.Movie;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class RentalService {
    private final WebClient webClient;

    public RentalService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie getMovieById(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id;
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new HttpClientErrorException(HttpStatus.NOT_FOUND)))
                .bodyToMono(Movie.class)
                .block();
    }

    public HttpStatusCode returnMovie(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id + "/availability/true";
        return webClient.patch()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> Mono.justOrEmpty(response.getStatusCode()))
                .blockOptional()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatusCode rentMovie(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id + "/availability/false";
        return webClient.patch()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> Mono.justOrEmpty(response.getStatusCode()))
                .blockOptional()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
