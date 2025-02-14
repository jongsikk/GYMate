package com.jongsik2.training.gymate.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractToken(request, "access_token");
        String refreshToken = extractToken(request, "refresh_token");

        if (accessToken != null && JwtUtil.validateToken(accessToken) == TokenStatus.VALID) {
            if (!authenticateUser(accessToken, request)) {
                handleInvalidUser(response);
                return;
            }
        } else if (refreshToken != null) {
            log.info("Access Token이 만료됨, Refresh Token 확인 중");

            if (JwtUtil.validateToken(refreshToken) == TokenStatus.VALID) {
                String email = JwtUtil.extractUsername(refreshToken);

                if (refreshTokenService.isValidRefreshToken(email, refreshToken)) {
                    // 새로운 Access Token 및 Refresh Token 발급
                    String newAccessToken = JwtUtil.generateAccessToken(email);
                    String newRefreshToken = JwtUtil.generateRefreshToken(email);
                    refreshTokenService.updateRefreshToken(email, newRefreshToken);

                    CookieUtil.addCookie(response, "access_token", newAccessToken, 60 * 30); // 30분 유지
                    CookieUtil.addCookie(response, "refresh_token", newRefreshToken, 60 * 60 * 24 * 7); // 7일 유지

                    if (!authenticateUser(newAccessToken, request)) {
                        handleInvalidUser(response);
                        return;
                    }

                    log.info("새로운 Access Token 발급 완료");
                } else {
                    log.warn("Refresh Token이 유효하지 않음. 재로그인 필요");
                    handleInvalidUser(response);
                    return;
                }
            } else {
                log.warn("Refresh Token이 만료됨. 재로그인 필요");
                handleInvalidUser(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean authenticateUser(String token, HttpServletRequest request) {
        String email = JwtUtil.extractUsername(token);
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    private String extractToken(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void handleInvalidUser(HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        CookieUtil.deleteCookie(response, "access_token");
        CookieUtil.deleteCookie(response, "refresh_token");
        response.sendRedirect("/login");
    }
}

