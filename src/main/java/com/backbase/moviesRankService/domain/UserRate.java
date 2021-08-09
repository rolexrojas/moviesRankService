package com.backbase.moviesRankService.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_rate")
    @JsonIgnore
    private Long user_id;
    @JsonIgnore
    private String username;
    @Column(name = "user_rated")
    private double userRated;
    @Column(name = "movie_identifier")
    private String movieIdentifier;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getUserRated() {
        return userRated;
    }

    public void setUserRated(double userRated) {
        this.userRated = userRated;
    }

    public String getMovieIdentifier() {
        return movieIdentifier;
    }

    public void setMovieIdentifier(String movieIdentifier) {
        this.movieIdentifier = movieIdentifier;
    }
}
