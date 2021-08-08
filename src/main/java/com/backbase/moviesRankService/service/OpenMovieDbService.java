package com.backbase.moviesRankService.service;

import com.backbase.moviesRankService.config.ApplicationProperties;
import com.backbase.moviesRankService.types.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenMovieDbService {

    @Autowired
    private ApplicationProperties applicationProperties;


    public MovieDTO findMovieInformation(String title){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationProperties.getOmdbUrl() + "/?t="+ title + "&apikey="+ applicationProperties.getOmdbApikey();
        System.out.println("omdb service to be called=> " + url);
        return restTemplate.postForObject(url, MovieDTO.class, MovieDTO.class);
    }
}
