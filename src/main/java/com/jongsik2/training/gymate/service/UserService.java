package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.dto.SignUpRequest;

public interface UserService {
    Long register(SignUpRequest signUpRequest);
}
