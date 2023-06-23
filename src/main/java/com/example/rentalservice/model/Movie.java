package com.example.rentalservice.model;


import org.bson.types.ObjectId;

public class Movie {
    private ObjectId id;
    private String title;

    private MovieGenre movieGenre;
    private Integer year;
    private String description;
    private boolean isAvailable = false;

    public Movie(String title, MovieGenre movieGenre, Integer year, String description) {

        this.title = title;
        this.movieGenre = movieGenre;
        this.year = year;
        this.description = description;
    }

    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", movie genre=" + movieGenre +
                ", year=" + year +
                ", description='" + description + '\'' +
                '}';
    }

    public ObjectId getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public MovieGenre getGenre() {
        return movieGenre;
    }

    public Integer getYear() {
        return year;
    }


    public String getDescription() {
        return description;
    }


    public boolean isAvailable() {
        return isAvailable;
    }
}
