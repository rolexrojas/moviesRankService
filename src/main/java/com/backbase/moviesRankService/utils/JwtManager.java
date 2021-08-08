package com.backbase.moviesRankService.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.backbase.moviesRankService.config.ApplicationProperties;
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

    public String generateJwtPayload(String username, String password) {
        return username +
                ":" +
                password;
    }

    public String generateToken(String username, String password) {

        String jwtPayLoad = generateJwtPayload(username, password);

        // TODO what exactly
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MINUTE, Integer.parseInt(applicationProperties.getTimeExpirationToken()));

        return Jwts.builder()
                .setSubject(jwtPayLoad)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, applicationProperties.getTokenSecret().getBytes())
                .compact();
    }
}
