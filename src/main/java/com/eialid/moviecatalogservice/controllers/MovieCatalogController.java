package com.eialid.moviecatalogservice.controllers;

import com.eialid.moviecatalogservice.models.Movie;
import com.eialid.moviecatalogservice.models.MovieCatalog;
import com.eialid.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogs/")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("{userId}")
    public List<MovieCatalog> getMovieLists(){
        List<Rating> ratings= Arrays.asList(
                new Rating(111, 4),
                new Rating(222, 5)
        );

        return ratings.stream().map(rating -> {
//           Movie movie=restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build() // webClient build method is implemented to get webCLient object
                    .get()                         // mentioning whether it is get or post
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())  // passing the uri from which it connects
                    .retrieve()                     // retrieve all the data that it gets
                    .bodyToMono(Movie.class)        // and converts it to Movie class type of object, bodyToMono is basically a emptyContainer u can say, it is type of promise where it is written what must be done when it gets the data. I am saying inside of this function, please convert the data to Movie object for me.. it is must for Webclient technique since we are doing asynchronous programming here.
                    .block();                       // It basically makes object ready and let know to me that it is ready now.
            return new MovieCatalog(movie.getName(), "A spiderman series of movies", rating.getRatings());
        }).collect(Collectors.toList());
    }
}
