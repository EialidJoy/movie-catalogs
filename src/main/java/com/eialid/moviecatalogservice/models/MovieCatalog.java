package com.eialid.moviecatalogservice.models;

import lombok.Data;

@Data
public class MovieCatalog {
    private String name;
    private String desc;
    private int ratings;

    public MovieCatalog(String name, String desc, int ratings) {
        this.name = name;
        this.desc = desc;
        this.ratings = ratings;
    }
}
