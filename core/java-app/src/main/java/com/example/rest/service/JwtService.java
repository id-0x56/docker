package com.example.rest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {

    private Algorithm algorithm = Algorithm.HMAC256("URWg9omMHvbNFRlI2snJfk9V1ZxqUkTTQtr5bB444HqGHA7OKXSfWWjnKZ3DzQ4r");

    public String create(String... args) {
        JWTCreator.Builder jwt = JWT.create();

        int i = 0;
        for (String arg: args) {
            jwt.withClaim(Integer.toString(i++), arg);
        }

        return jwt.sign(algorithm);
    }

    public DecodedJWT verify(String jwt) {
        return JWT.require(algorithm).build().verify(jwt);
    }

    public boolean validate(UserDetails userDetails, String jwt) {
        final String username = this.verify(jwt).getClaim("0").asString();
        
        return username.equals(userDetails.getUsername());
    }
}
