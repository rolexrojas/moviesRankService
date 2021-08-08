package com.backbase.moviesRankService.repository;

import com.backbase.moviesRankService.domain.NomineeMovie;
import org.springframework.data.repository.CrudRepository;

public interface NomineeMovieRepository extends CrudRepository<NomineeMovie, Long> {
    NomineeMovie findFirstByCategoryAndNomineeIgnoreCaseAndWon(String category, String nominee, String won);
}
