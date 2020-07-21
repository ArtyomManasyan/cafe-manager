package com.cafeManagerAssignment.cafeManager.service.impl;

import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.model.UserPrincipal;
import com.cafeManagerAssignment.cafeManager.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTTokenService implements TokenService {

    private static final String JWT_SECRET = "somestrongkeysomestrongkeysomestrongkey";

    @Override
    public String generateToken(User user) {
        Instant expirationTime = Instant.now().plus(2, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

        String compactTokenString = Jwts.builder()
                .claim("id", user.getId())
                .claim("sub", user.getUsername())
                .claim("admin", user.isManager())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return "Bearer " + compactTokenString;
    }

    @Override
    public UserPrincipal parseToken(String token) {
        byte[] secretBytes = JWT_SECRET.getBytes();

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(token);

        String username = jwsClaims.getBody()
                .getSubject();
        Long userId = jwsClaims.getBody()
                .get("id", Long.class);
        boolean isAdmin = jwsClaims.getBody().get("admin", Boolean.class);

        return new UserPrincipal(userId, username, isAdmin);
    }
}
