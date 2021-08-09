package com.backbase.moviesRankService.utils;

public class RateCalculator {

    private final static int BASE_RATE = 10;

    public static double ratingCalculator(double userRating, int totalVotes, double lastRating){
        //double earnedRatingPoints = ((userRating * BASE_RATE) / BASE_RATE);
        return (userRating + lastRating) / totalVotes;
    }
}
