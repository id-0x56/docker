package com.example.rest.service;

import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtServiceImpl implements JwtService {

    private final Algorithm algorithm;

    public JwtServiceImpl(Environment env) {
        this.algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret"));
    }

    @Override
    public String create(String... args) {
        JWTCreator.Builder jwt = JWT.create();

        int i = 0;
        for (String arg: args) {
            jwt.withClaim(Integer.toString(i++), arg);
        }

        return jwt.sign(this.algorithm);
    }

    @Override
    public String get(String jwt, String key) {
        return JWT.require(this.algorithm).build().verify(jwt).getClaim(key).asString();
    }

    @Override
    public boolean validate(UserDetails userDetails, String jwt) {
        final String username = this.get(jwt, "0");
        
        return username.equals(userDetails.getUsername());
    }
}
