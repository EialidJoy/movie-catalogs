package com.eialid.moviecatalogservice.services;

import com.eialid.moviecatalogservice.models.MovieCatalog;
import com.eialid.moviecatalogservice.models.Rating;
import com.eialid.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingsInfo {

    @Autowired
    private RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRatings getUserRatings(String userId) {
        return restTemplate.getForObject("http://ratings-info-service/ratings/users/" + userId, UserRatings.class);
    }

    public MovieCatalog getFallBackUserRating(Rating rating){
        return new MovieCatalog("Movies not found", "", rating.getRatings());
    }

}
