package com.manga.identityservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.signerKey}")
    private String signerKey;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(signerKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;  // Return false if token is invalid
        }
    }

    // Extract username from JWT token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(signerKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
