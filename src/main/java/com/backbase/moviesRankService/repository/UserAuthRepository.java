package com.backbase.moviesRankService.repository;

import com.backbase.moviesRankService.domain.UserAuth;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuth, Long> {

    UserAuth findUserAuthByUsername(String Username);
}