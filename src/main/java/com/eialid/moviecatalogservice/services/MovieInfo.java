package com.eialid.moviecatalogservice.services;

import com.eialid.moviecatalogservice.models.Movie;
import com.eialid.moviecatalogservice.models.MovieCatalog;
import com.eialid.moviecatalogservice.models.Rating;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackMovie",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        }
    )
    public MovieCatalog getMovie(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new MovieCatalog(movie.getName(),movie.getOverview(),rating.getRatings());
    }

    public MovieCatalog getFallBackMovie(Rating rating){
        return new MovieCatalog("Movies not found", "", rating.getRatings());
    }


}
