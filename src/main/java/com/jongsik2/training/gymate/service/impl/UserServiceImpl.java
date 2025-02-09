package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.SignUpRequest;
import com.jongsik2.training.gymate.repository.UserRepository;
import com.jongsik2.training.gymate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Long register(SignUpRequest signUpRequest) {
        log.info("password : " + bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(User.builder()
                        .email(signUpRequest.getEmail())
                        .username(signUpRequest.getUsername())
                        .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .roleName("USER")
                        .build())
                .getId();
    }
}