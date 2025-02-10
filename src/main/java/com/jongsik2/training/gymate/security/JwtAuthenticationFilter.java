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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractToken(request, "access_token");
        String refreshToken = extractToken(request, "refresh_token");

        if (accessToken != null && jwtUtil.validateToken(accessToken) == TokenStatus.VALID) {
            authenticateUser(accessToken, request);
        } else if (refreshToken != null) {
            log.info("Access Token이 만료됨, Refresh Token 확인 중");

            if (jwtUtil.validateToken(refreshToken) == TokenStatus.VALID) {
                String email = jwtUtil.extractUsername(refreshToken);

                if (refreshTokenService.isValidRefreshToken(email, refreshToken)) {
                    String newAccessToken = jwtUtil.generateAccessToken(email);
                    String newRefreshToken = jwtUtil.generateRefreshToken(email);

                    refreshTokenService.updateRefreshToken(email, newRefreshToken);

                    addCookie(response, "access_token", newAccessToken, 60 * 30); // 30분 유지
                    addCookie(response, "refresh_token", newRefreshToken, 60 * 60 * 24); // 7일 유지

                    authenticateUser(newAccessToken, request);

                    log.info("새로운 Access Token 발급 완료");
                } else {
                    log.warn("Refresh Token이 유효하지 않음. 재로그인 필요");
                    SecurityContextHolder.clearContext();
                    response.sendRedirect("/login?error=expired");
                    return;
                }
            } else {
                log.warn("Refresh Token이 만료됨. 재로그인 필요");
                SecurityContextHolder.clearContext();
                response.sendRedirect("/login?error=expired");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token, HttpServletRequest request) {
        String email = jwtUtil.extractUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
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

    private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
