package com.backbase.moviesRankService.controller;

import com.backbase.moviesRankService.domain.MovieRating;
import com.backbase.moviesRankService.domain.NomineeMovie;
import com.backbase.moviesRankService.domain.UserRate;
import com.backbase.moviesRankService.repository.MovieRateRepository;
import com.backbase.moviesRankService.repository.NomineeMovieRepository;
import com.backbase.moviesRankService.repository.UserRateRepository;
import com.backbase.moviesRankService.service.OpenMovieDbService;
import com.backbase.moviesRankService.service.UserRateService;
import com.backbase.moviesRankService.types.*;
import com.backbase.moviesRankService.utils.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
public class MovieRankController {

     @Autowired
     private OpenMovieDbService openMovieDbService;

     @Autowired
     private NomineeMovieRepository nomineeMovieRepository;

     @Autowired
     private JwtManager jwtManager;

     @Autowired
     private UserRateService userRateService;

     private final String YES = "YES";
     private final String NO = "NO";
     private final int DEFAULT_RATING = 10;

     @PostMapping(path="/rateMovie")
     private ResponseEntity rateMovie(@RequestBody MovieRateDTO movieRateDTO, @RequestHeader(required = true, name = "Authorization") String token){
         //no ratings can be greater than 10 or minus cero
         if(movieRateDTO.getRating() > DEFAULT_RATING || movieRateDTO.getRating() <= 0){
             ResponseEntity.status(400).body("{'Exceeded': 'Rating has to be between 0 and 10'}");
         }

         if(!jwtManager.validateToken(token)){
             ResponseEntity.status(400).body("{'Error': 'Invalid token'}");
         }

         TokenDataDTO tokenDataDTO = jwtManager.parseTokenToModel(token);

         //looking for previous user rate
         UserRate userRateFound = userRateService.checkIfPreviousRateExists(movieRateDTO.getMovieIdentifier(), tokenDataDTO.getUsername());

         if(userRateFound == null) {
             //create new rate if doesn't exist
             UserRate savedUserRate = userRateService.createNewUserRate(movieRateDTO.getMovieIdentifier(), movieRateDTO.getRating(), tokenDataDTO.getUsername());

             if(savedUserRate != null){
                 userRateService.updateMovieRating(movieRateDTO.getMovieIdentifier(), movieRateDTO.getRating());
             }
             return ResponseEntity.status(201).build();
         }
         return ResponseEntity.status(400).body("{'Duplicated': 'This movie was already ranked by this user'}");
     }

     @GetMapping(path="/findNomineeMovieByTitle/{nomineeMovieTitle}")
     private ResponseEntity findNomineeMovieByTitle(@PathVariable("nomineeMovieTitle") String nomineeMovieTitle, @RequestHeader(required = true, name = "Authorization") String token) throws ExecutionException, InterruptedException {

          CompletableFuture<MovieDTO> openMovieDb = CompletableFuture.supplyAsync(() ->  openMovieDbService.findMovieInformation(nomineeMovieTitle));
          CompletableFuture<NomineeMovie> nomineeMovieDb = CompletableFuture.supplyAsync(() -> nomineeMovieRepository.findFirstByCategoryAndNomineeIgnoreCaseAndWon(MovieCategory.BEST_PICTURE_CATEGORY.getCategory(), nomineeMovieTitle, YES));

          CompletableFuture.allOf(nomineeMovieDb, openMovieDb).join();
          if(nomineeMovieDb.isDone() && openMovieDb.isDone()){
               NomineeMovie byCategoryAnAndNominee = nomineeMovieDb.get();
               openMovieDb.get().setWonBestPicture(byCategoryAnAndNominee == null ? NO : YES);
               return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(openMovieDb.get());
          }
          return ResponseEntity.status(404).build();
     }

     @GetMapping(path="/topTenMoviesByBoxOffice")
     private ResponseEntity<List<ImdbDTO>> topTenRatedMoviesByBoxOffice(@RequestHeader(required = true, name = "Authorization") String token){

         //First get top ten rated Movies (Based on my internal rating)
         List<MovieRating> topTenRatedMovies = userRateService.getTopTenRatedMovies();
         List<ImdbDTO> openMovieList = new ArrayList<>();
         for (MovieRating movieRate: topTenRatedMovies) {

             ImdbDTO movieInformationByimdbId = openMovieDbService.findMovieInformationByimdbId(movieRate.getMovieIdentifier());
             movieInformationByimdbId.setInternalCalulatedRate(String.valueOf(movieRate.getCalculatedRating()));
             if(movieInformationByimdbId.getBoxOfficeValue() == null){
                 movieInformationByimdbId.setBoxOfficeValue("0.0");
             }
             openMovieList.add(movieInformationByimdbId);
         }

         List<ImdbDTO> sortedList = openMovieList.stream().sorted(Comparator.comparing(ImdbDTO::getBoxOfficeValue)).collect(Collectors.toList());

         return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(sortedList);
     }
}
