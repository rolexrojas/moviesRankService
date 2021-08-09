package com.backbase.moviesRankService.repository;

import com.backbase.moviesRankService.domain.NomineeMovie;
import com.backbase.moviesRankService.domain.UserRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NomineeMovieRepository extends CrudRepository<NomineeMovie, Long> {
    NomineeMovie findFirstByCategoryAndNomineeIgnoreCaseAndWon(String category, String nominee, String won);
}
