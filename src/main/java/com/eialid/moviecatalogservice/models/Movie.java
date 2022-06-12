package com.eialid.moviecatalogservice.models;

public class Movie {
    private int movieId;
    private String name;
    private String overview;


    public Movie(int movieId, String name, String overview) {
        this.movieId = movieId;
        this.name = name;
        this.overview = overview;
    }

    public Movie() {
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
