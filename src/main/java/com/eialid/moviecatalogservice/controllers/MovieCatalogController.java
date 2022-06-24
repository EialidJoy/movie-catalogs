package com.eialid.moviecatalogservice.controllers;

import com.eialid.moviecatalogservice.models.MovieCatalog;
import com.eialid.moviecatalogservice.models.UserRatings;
import com.eialid.moviecatalogservice.services.MovieInfo;
import com.eialid.moviecatalogservice.services.UserRatingsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogs/")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingsInfo userRatingsInfo;

    @RequestMapping("{userId}")
    public List<MovieCatalog> getMovieLists(@PathVariable("userId") String userId){
        UserRatings ratings= userRatingsInfo.getUserRatings(userId);

        return ratings.getUserRatings().stream().map(rating -> movieInfo.getMovie(rating)).collect(Collectors.toList());

           /* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();                       */

    }

    @GetMapping("/dockerMessages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}
