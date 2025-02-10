package com.jongsik2.training.gymate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final long REFRESH_TOKEN_EXPIRE = 60 * 60 * 24;

    public void saveRefreshToken(String email, String refreshToken) {
        redisTemplate.opsForValue().set("refresh:" + email, refreshToken, REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);
    }

    public boolean isValidRefreshToken(String email, String refreshToken) {
        String storedToken = redisTemplate.opsForValue().get("refresh:" + email);
        return storedToken != null && storedToken.equals(refreshToken);
    }

    public void deleteRefreshToken(String email) {
        redisTemplate.delete("refresh:" + email);
    }

    public void updateRefreshToken(String email, String refreshToken) {
        deleteRefreshToken(email);
        saveRefreshToken(email, refreshToken);
    }
}
