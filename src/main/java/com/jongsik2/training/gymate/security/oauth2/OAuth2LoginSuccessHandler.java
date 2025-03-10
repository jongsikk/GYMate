package com.jongsik2.training.gymate.security.oauth2;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.repository.UserRepository;
import com.jongsik2.training.gymate.security.CookieUtil;
import com.jongsik2.training.gymate.security.JwtUtil;
import com.jongsik2.training.gymate.security.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        String accessToken = JwtUtil.generateAccessToken(email);
        String refreshToken = JwtUtil.generateRefreshToken(email);

        refreshTokenService.saveRefreshToken(authentication.getName(), refreshToken);

        CookieUtil.addCookie(response, "access_token", accessToken, 60 * 30); // 30분 유지
        CookieUtil.addCookie(response, "refresh_token", refreshToken, 60 * 60 * 24); // 7일 유지

        response.sendRedirect("/");
    }

}
