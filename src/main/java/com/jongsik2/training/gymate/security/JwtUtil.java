package com.jongsik2.training.gymate.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.access-secret}")
    private String accessSecret;
    @Value("${jwt.access-expiration}")
    private Long accessExpiration;
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;
    @Value("${jwt.issuer}")
    private String issuer;
    @Getter
    private static final String tokenType = "Bearer";

    private static String ACCESS_SECRET;
    private static Long ACCESS_EXPIRATION;
    private static Long REFRESH_EXPRIATION;
    private static String ISSUER;

    @PostConstruct
    public void init() {
        ACCESS_SECRET = this.accessSecret;
        ACCESS_EXPIRATION = this.accessExpiration;
        REFRESH_EXPRIATION = this.refreshExpiration;
        ISSUER = this.issuer;
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public static Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(ACCESS_SECRET.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public static String generateAccessToken(String email) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(ACCESS_SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String generateRefreshToken(String email) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setIssuer(ISSUER)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPRIATION))
                .signWith(Keys.hmacShaKeyFor(ACCESS_SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return header;
    }

//    private Map<String, Object> createClaims(String email) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", user.getEmail());
//        claims.put("role", user.getRoleName());
//
//        return claims;
//    }

    public static TokenStatus validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(ACCESS_SECRET.getBytes())).build().parseClaimsJws(token); // 토큰 파싱하여 유효성 검증
            return TokenStatus.VALID;
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            return TokenStatus.INVALID;
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return TokenStatus.INVALID;
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            return TokenStatus.EXPIRED;
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            return TokenStatus.INVALID;
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
            return TokenStatus.INVALID;
        }
    }
}
