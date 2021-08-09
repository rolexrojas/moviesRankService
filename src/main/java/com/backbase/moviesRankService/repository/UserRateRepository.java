package com.backbase.moviesRankService.repository;

import com.backbase.moviesRankService.domain.UserRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRateRepository extends CrudRepository<UserRate, Long> {
    UserRate findByMovieIdentifierAndUsername(String movieIdentifier, String idUser);
    List<UserRate> findTop10ByUserRatedLessThanOrderByUserRatedAsc(double userRate);
}
