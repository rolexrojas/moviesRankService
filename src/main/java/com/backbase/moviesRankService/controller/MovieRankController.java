package com.backbase.moviesRankService.controller;

import com.backbase.moviesRankService.domain.NomineeMovie;
import com.backbase.moviesRankService.repository.NomineeMovieRepository;
import com.backbase.moviesRankService.service.OpenMovieDbService;
import com.backbase.moviesRankService.types.MovieCategory;
import com.backbase.moviesRankService.types.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class MovieRankController {

     @Autowired
     private OpenMovieDbService openMovieDbService;

     @Autowired
     private NomineeMovieRepository nomineeMovieRepository;

     final static String YES = "YES";
     final static String NO = "NO";

     private ExecutorService executor = Executors.newSingleThreadExecutor();

     @PostMapping(path="/wonBestPicture")
     private void doesThisMovieWonBestPicture(String movieTitle){


     }

     @PostMapping(path="/rateMovie")
     private void rateMovie(String movie, int rating){

     }

     @GetMapping(path="/findNomineeMovieByTitle/{nomineeMovieTitle}")
     private ResponseEntity findNomineeMovieByTitle(@PathVariable("nomineeMovieTitle") String nomineeMovieTitle) throws ExecutionException, InterruptedException {

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

     @PostMapping(path="/topTenMoviesByBoxOffice")
     private List<String> topTenRatedMoviesByBoxOffice(){

         return new ArrayList<>();
     }
}
