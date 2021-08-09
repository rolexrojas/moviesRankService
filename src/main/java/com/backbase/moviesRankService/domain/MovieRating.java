package com.backbase.moviesRankService.domain;

import javax.persistence.*;

@Entity
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie_rate")
    private Long id;

    @Column(name = "movie_identifier")
    private String movieIdentifier;

    @Column(name = "voting_count")
    private int votingCount;

    @Column(name = "calculated_rating")
    private double calculatedRating;

    public String getMovieIdentifier() {
        return movieIdentifier;
    }

    public void setMovieIdentifier(String movieIdentifier) {
        this.movieIdentifier = movieIdentifier;
    }

    public int getVotingCount() {
        return votingCount;
    }

    public void setVotingCount(int votingCount) {
        this.votingCount = votingCount;
    }

    public double getCalculatedRating() {
        return calculatedRating;
    }

    public void setCalculatedRating(double calculatedRating) {
        this.calculatedRating = calculatedRating;
    }
}
