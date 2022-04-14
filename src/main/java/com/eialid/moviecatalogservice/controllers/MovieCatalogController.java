package com.eialid.moviecatalogservice.controllers;

import com.eialid.moviecatalogservice.models.MovieCatalog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalogs/")
public class MovieCatalogController {

    @RequestMapping("{userId}")
    public List<MovieCatalog> getMovieLists(){
        
        return Collections.singletonList(new MovieCatalog("SpiderMan:No Way Home", "A spiderman series of movies", 4));
    }
}
