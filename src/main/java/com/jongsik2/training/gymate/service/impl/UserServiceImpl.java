package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.SignUpRequest;
import com.jongsik2.training.gymate.repository.UserRepository;
import com.jongsik2.training.gymate.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public boolean register(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return false;
        }

        try {
            userRepository.save(User.builder()
                    .email(signUpRequest.getEmail())
                    .username(signUpRequest.getUsername())
                    .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .roleName("USER")
                    .build());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}