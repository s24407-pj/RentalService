package com.example.rentalservice.service;

import com.example.rentalservice.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class RentalService {
    private final WebClient webClient;

    public RentalService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie getMovieById(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id;
        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }

    public HttpStatusCode returnMovie(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id + "/availability/true";
        WebClient webClient = WebClient.create();

        return webClient.patch()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode();
    }

    public HttpStatusCode rentMovie(ObjectId id) {
        String url = "http://localhost:8080/movies/" + id + "/availability/false";
        WebClient webClient = WebClient.create();

        return webClient.patch()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode();
    }
}
