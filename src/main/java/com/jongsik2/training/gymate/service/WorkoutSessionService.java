package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.SetRecordResponse;
import com.jongsik2.training.gymate.dto.WorkoutRequest;
import com.jongsik2.training.gymate.dto.WorkoutSessionResponse;

import java.util.List;

public interface WorkoutSessionService {
    boolean save(User user, WorkoutRequest workoutRequest);

    List<WorkoutSessionResponse> getWorkoutHistoryByUserId(Long userId);

    WorkoutSessionResponse getWorkoutSessionById(Long id);

    List<SetRecordResponse> getSetRecordsByWorkoutSession(Long id);
}
