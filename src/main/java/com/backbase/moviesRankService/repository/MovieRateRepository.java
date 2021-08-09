package com.backbase.moviesRankService.repository;

import com.backbase.moviesRankService.domain.MovieRating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRateRepository extends CrudRepository<MovieRating, Long> {
    MovieRating findByMovieIdentifier(String movieIdentifier);
    List<MovieRating> findTop10ByCalculatedRatingLessThanOrderByCalculatedRating(double userRate);
}
