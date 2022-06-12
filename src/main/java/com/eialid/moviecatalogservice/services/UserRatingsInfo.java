package com.eialid.moviecatalogservice.services;

import com.eialid.moviecatalogservice.models.MovieCatalog;
import com.eialid.moviecatalogservice.models.Rating;
import com.eialid.moviecatalogservice.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingsInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRatings getUserRatings(String userId) {
        return restTemplate.getForObject("http://ratings-info-service/ratings/users/" + userId, UserRatings.class);
    }

    public UserRatings getFallBackUserRating(String userId){
        UserRatings userRatings=new UserRatings();
        userRatings.setUserRatings(
                Arrays.asList(new Rating(0,0))
        );
        return userRatings;
    }
}
