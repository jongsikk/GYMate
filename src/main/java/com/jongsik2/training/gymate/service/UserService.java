package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.SignUpRequest;

public interface UserService {
    boolean register(SignUpRequest signUpRequest);

    User getCurrentUser();
}
