package com.example.ClimateChangeBackend.security;


import com.example.ClimateChangeBackend.security.services.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecret;

    @Value("${application.security.jwt.expiration}")
    private int jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshTokenExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .subject(customUserDetails.getRut())
                .claim("firstName",customUserDetails.getFirstName())
                .claim("lastName",customUserDetails.getLastName())
                .claim("email",customUserDetails.getEmail())
                .claim("role",customUserDetails.getRole())
                .claim("authorities",customUserDetails.getAuthorities())
                .claim("passwordHash", customUserDetails.getPassword().hashCode())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+jwtExpiration))
                .signWith(getSigningKey(),Jwts.SIG.HS512)
                .compact();

    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return  Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateJwtToken(String jwt) {
        try{
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch(SignatureException e){
            System.err.println("Invalid JWT signature: " + e.getMessage());
        }catch(MalformedJwtException e){
            System.err.println("Invalid JWT token: " + e.getMessage());
        }catch(ExpiredJwtException e){
            System.err.println("Expired JWT token: " + e.getMessage());
        }catch(UnsupportedJwtException e){
            System.err.println("Unsupported JWT token: " + e.getMessage());
        }catch(IllegalArgumentException e){
            System.err.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;

    }

    public boolean isTokenExpired(String token){
        try{
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return false;
        }catch(ExpiredJwtException e){
            return true;
        }catch (Exception e){
            System.err.println("Failed to check expiration: " + e.getMessage());
        }
        return true;
    }

    public String getUsername(String jwt) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload().getSubject();
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    public long getRefreshExpiryTime() {
        return System.currentTimeMillis() + refreshTokenExpirationMs;
    }
}
