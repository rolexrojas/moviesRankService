package com.backbase.moviesRankService.service;

import com.backbase.moviesRankService.domain.MovieRating;
import com.backbase.moviesRankService.domain.NomineeMovie;
import com.backbase.moviesRankService.domain.UserRate;
import com.backbase.moviesRankService.repository.MovieRateRepository;
import com.backbase.moviesRankService.repository.UserRateRepository;
import com.backbase.moviesRankService.types.MovieRateDTO;
import com.backbase.moviesRankService.utils.RateCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRateService {

    @Autowired
    private UserRateRepository userRateRepository;

    @Autowired
    private MovieRateRepository movieRateRepository;

    public UserRate checkIfPreviousRateExists(String movieId, String username){
        return userRateRepository.findByMovieIdentifierAndUsername(movieId, username);
    }

    public UserRate createNewUserRate(String movieId, double movieRate, String username){
        UserRate userRate = new UserRate();
        userRate.setMovieIdentifier(movieId);
        userRate.setUsername(username);
        userRate.setUserRated(movieRate);
        return userRateRepository.save(userRate);

    }

    public void updateMovieRating(String movieId, double newRating){
        MovieRating movieRated = movieRateRepository.findByMovieIdentifier(movieId);
        if(movieRated == null){
            //create first time rating for a Movie
            MovieRating movieRating = new MovieRating();
            movieRating.setMovieIdentifier(movieId);
            movieRating.setVotingCount(1);
            movieRating.setCalculatedRating(RateCalculator.ratingCalculator(newRating, movieRating.getVotingCount(), 0.0));
            movieRateRepository.save(movieRating);
            return;
        }

        assert movieRated != null;
        movieRated.setVotingCount(movieRated.getVotingCount() + 1);
        movieRated.setCalculatedRating(RateCalculator.ratingCalculator(newRating, movieRated.getVotingCount() + 1, movieRated.getCalculatedRating()));
        movieRateRepository.save(movieRated);

    }

    public List<MovieRating> getTopTenRatedMovies() {
        return movieRateRepository.findTop10ByCalculatedRatingLessThanOrderByCalculatedRating(10.0D);
    }
}
