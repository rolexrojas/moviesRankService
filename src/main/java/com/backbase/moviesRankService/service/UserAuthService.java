package com.backbase.moviesRankService.service;

import com.backbase.moviesRankService.domain.UserAuth;
import com.backbase.moviesRankService.types.SignUpDTO;
import com.backbase.moviesRankService.utils.JwtManager;
import com.backbase.moviesRankService.utils.Validator;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserAuthService {


    private void BlockUser(){
        System.out.println("UserAuthService");
    }

    private void updateUserFailedRetryCount(){}

}
