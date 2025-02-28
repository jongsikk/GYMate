package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.WorkoutRequest;

public interface WorkoutSessionService {
    boolean save(User user, WorkoutRequest workoutRequest);
}
