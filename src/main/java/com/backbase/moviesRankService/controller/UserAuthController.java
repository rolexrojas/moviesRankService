package com.backbase.moviesRankService.controller;

import com.backbase.moviesRankService.domain.UserAuth;
import com.backbase.moviesRankService.repository.UserAuthRepository;
import com.backbase.moviesRankService.service.UserAuthService;
import com.backbase.moviesRankService.types.SignInDTO;
import com.backbase.moviesRankService.types.SignUpDTO;
import com.backbase.moviesRankService.utils.JwtManager;
import com.backbase.moviesRankService.utils.PasswordStorage;
import com.backbase.moviesRankService.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserAuthController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordStorage passwordStorage;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping(path="/userSignIn")
    public ResponseEntity userSignIn(@RequestBody SignInDTO signInDTO){

        boolean isValid = Validator.isValidSignIn(signInDTO);
        if(!isValid){
            return ResponseEntity.status(400).build();
        }
        try {
            UserAuth userAccount = userAuthRepository.findUserAuthByUsername(signInDTO.getUsername());
            boolean isVerifiedPassword = passwordStorage.verifyPassword(signInDTO.getPassword(), userAccount.getPassword());

            System.out.println("password verified" + isVerifiedPassword);
            if(!isVerifiedPassword){
                return ResponseEntity.status(403).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(401).build();
        }

        String tokenResponse = jwtManager.generateToken(signInDTO.getUsername(), signInDTO.getPassword());


        //here userAuth Service take the account and generate token
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).header("token", tokenResponse).build();
    }


    @PostMapping(path="/userSignUp")
    public ResponseEntity userSignUp(@RequestBody SignUpDTO signUpDTO){

        boolean isValid = Validator.isValidSignUp(signUpDTO);
        if(!isValid){
            return ResponseEntity.status(400).build();
        }
        String hash = "";
        try {
            hash = passwordStorage.createHash(signUpDTO.getPassword());
        }catch (Exception e){
            return ResponseEntity.status(401).build();
        }
        UserAuth userAuth = new UserAuth(signUpDTO.getUsername(), hash, false);
        final UserAuth createdAuth = userAuthRepository.save(userAuth);
        System.out.println("Created user account");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(signUpDTO.toString());
    }


}
