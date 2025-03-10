package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.LoginRequest;
import com.jongsik2.training.gymate.dto.SignUpRequest;
import com.jongsik2.training.gymate.security.CookieUtil;
import com.jongsik2.training.gymate.security.JwtUtil;
import com.jongsik2.training.gymate.security.RefreshTokenService;
import com.jongsik2.training.gymate.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String index(Model model) {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginRequest loginRequest, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            String accessToken = JwtUtil.generateAccessToken(loginRequest.getEmail());
            String refreshToken = JwtUtil.generateRefreshToken(loginRequest.getEmail());

            refreshTokenService.saveRefreshToken(authentication.getName(), refreshToken);

            CookieUtil.addCookie(response, "access_token", accessToken, 60 * 30); // 30분 유지
            CookieUtil.addCookie(response, "refresh_token", refreshToken, 60 * 60 * 24); // 7일 유지

            return "redirect:/";
        } catch (BadCredentialsException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/login";
        }
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignUpRequest signUpRequest, RedirectAttributes redirectAttributes) {
        boolean isSuccess = userService.register(signUpRequest);

        if (isSuccess) {
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("message", "회원가입 실패. 다시 시도해주세요.");
            return "redirect:/signup";
        }
    }
}
