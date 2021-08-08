package com.backbase.moviesRankService.domain;

import javax.persistence.*;

@Entity
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie_rank")
    private Long id;

    private String movieIdentifier;

    @Column(name = "user_id")
    private Long user_id;

    private int user_rating;

    private int voting_count;

    public String getMovieIdentifier() {
        return movieIdentifier;
    }

    public void setMovieIdentifier(String movieIdentifier) {
        this.movieIdentifier = movieIdentifier;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    public int getVoting_count() {
        return voting_count;
    }

    public void setVoting_count(int voting_count) {
        this.voting_count = voting_count;
    }
}
