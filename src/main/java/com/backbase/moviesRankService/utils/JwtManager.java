package com.backbase.moviesRankService.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.backbase.moviesRankService.config.ApplicationProperties;
import com.backbase.moviesRankService.types.TokenDataDTO;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtManager {

    private Logger log = LogManager.getLogger(JwtManager.class);

    @Autowired
    private ApplicationProperties applicationProperties;


    public String generateToken(String username, String password) {

        String jwtPayLoad = generateJwtPayload(username, 20201188L);
        System.out.println("GeneratePayload => " + jwtPayLoad);
        // TODO what exactly
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MINUTE, Integer.parseInt(applicationProperties.getTimeExpirationToken()));

        return Jwts.builder()
                .setSubject(jwtPayLoad)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, applicationProperties.getTokenSecret().getBytes())
                .compact();
    }

    private String parseToken(String token) {
        return Jwts.parser().setSigningKey(applicationProperties.getTokenSecret().getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateJwtPayload(String username, Long code) {
        return username + ":" + code;
    }


    public boolean validateToken(String token) throws JwtException {
        parseToken(token);
        return true;
    }

    public TokenDataDTO parseTokenToModel(String token) {

        token = token.replace("Bearer ", "");

        String parseToken = parseToken(token);

        String[] split = parseToken.split(":");

        if (split.length <= 0)
            return null;

        String username = split[0];

        return new TokenDataDTO(username);
    }
}
