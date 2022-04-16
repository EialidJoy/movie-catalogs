package com.eialid.moviecatalogservice.models;

public class Movie {
    private int movieId;
    private String name;

    public Movie() {
    }

    public Movie(int movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }
}
