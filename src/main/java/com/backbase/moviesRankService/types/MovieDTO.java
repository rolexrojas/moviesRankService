package com.backbase.moviesRankService.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDTO {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("BoxOffice")
    private String boxOfficeValue;
    @JsonProperty("Production")
    private String production;
    @JsonProperty("imdbID")
    private String imdbID;
    private String wonBestPicture;
  // "Metascore":"74","imdbRating":"8.8","imdbVotes":"2,135,128","imdbID":"tt1375666","Type":"movie","DVD":"20 Jun 2013","BoxOffice":"$292,576,195","Production":"Syncopy, Warner Bros.","Website":"N/A","Response":"True"}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() { return director; }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getBoxOfficeValue() {
        return boxOfficeValue;
    }

    public void setBoxOfficeValue(String boxOfficeValue) {
        this.boxOfficeValue = boxOfficeValue;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWonBestPicture() { return wonBestPicture; }

    public void setWonBestPicture(String wonBestPicture) { this.wonBestPicture = wonBestPicture; }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}

