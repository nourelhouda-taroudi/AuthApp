package com.devoir.auth.utils;

import com.devoir.auth.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("jwt.secret")
    private String JWT_SECRET;

    @Value("#{ T(java.lang.Integer).parseInt('${jwt.ttl}')}")
    private Integer JWT_TTL;

    public String generateToken(User utilisateur) {
        String subject = getSubjectFromUser(utilisateur);

        Map<String, Object> claims = new HashMap<>();
        // claims.put("authorities", user.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * JWT_TTL))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, User user) {
        final String subject = extractSubject(token);
        return (subject.equals(getSubjectFromUser(user)) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String getSubjectFromUser(User user) {
        return user.getId().toString();
    }
}
