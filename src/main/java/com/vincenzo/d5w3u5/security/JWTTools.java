package com.vincenzo.d5w3u5.security;

import com.vincenzo.d5w3u5.entity.Utente;
import com.vincenzo.d5w3u5.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Utente user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 86400000))  // 24 ore
                .setSubject(String.valueOf(user.getId()))  // Soggetto = user.Id
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  //  HMAC SHA-256
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Token non valido, ritenta il Login");
        }
    }

    public Long extractIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());  // user.Id
    }
}
