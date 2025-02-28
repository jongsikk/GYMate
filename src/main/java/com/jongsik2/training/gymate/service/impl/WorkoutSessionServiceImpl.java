package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.SetRecord;
import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.domain.WorkoutSession;
import com.jongsik2.training.gymate.dto.SetRecordRequest;
import com.jongsik2.training.gymate.dto.WorkoutRequest;
import com.jongsik2.training.gymate.repository.SetRecordRepository;
import com.jongsik2.training.gymate.repository.WorkoutSessionRepository;
import com.jongsik2.training.gymate.service.ExerciseService;
import com.jongsik2.training.gymate.service.WorkoutSessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final ExerciseService exerciseService;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final SetRecordRepository setRecordRepository;

    @Override
    @Transactional
    public boolean save(User user, WorkoutRequest workoutRequest) {
        try {
            WorkoutSession workoutSession = WorkoutSession.builder()
                    .user(user)
                    .exercise(exerciseService.getExerciseByExerciseId(workoutRequest.getExerciseId()))
                    .startTime(workoutRequest.getStartTime())
                    .endTime(workoutRequest.getEndTime())
                    .totalTime(workoutRequest.getTotalWorkoutTime())
                    .totalRestTime(workoutRequest.getTotalRestTime())
                    .build();
            workoutSessionRepository.save(workoutSession);

            for (SetRecordRequest setRecordRequest : workoutRequest.getSets()) {
                setRecordRepository.save(SetRecord.builder()
                        .workoutSession(workoutSession)
                        .setNumber(setRecordRequest.getSetNumber())
                        .workoutTime(setRecordRequest.getTime())
                        .reps(setRecordRequest.getReps())
                        .build());
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
