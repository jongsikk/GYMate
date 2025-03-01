package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.SetRecord;
import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.domain.WorkoutSession;
import com.jongsik2.training.gymate.dto.SetRecordRequest;
import com.jongsik2.training.gymate.dto.SetRecordResponse;
import com.jongsik2.training.gymate.dto.WorkoutRequest;
import com.jongsik2.training.gymate.dto.WorkoutSessionResponse;
import com.jongsik2.training.gymate.repository.SetRecordRepository;
import com.jongsik2.training.gymate.repository.WorkoutSessionRepository;
import com.jongsik2.training.gymate.service.ExerciseService;
import com.jongsik2.training.gymate.service.WorkoutSessionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final ExerciseService exerciseService;
    private final SetRecordRepository setRecordRepository;
    private final WorkoutSessionRepository workoutSessionRepository;

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
                        .workoutTime(setRecordRequest.getWorkoutTime())
                        .weight(setRecordRequest.getWeight())
                        .reps(setRecordRequest.getReps())
                        .build());
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<WorkoutSessionResponse> getWorkoutHistoryByUserId(Long userId) {
        return workoutSessionRepository.findAllByUserId(userId)
                .stream()
                .map(WorkoutSession::toDto)
                .toList();
    }

    @Override
    public WorkoutSessionResponse getWorkoutSessionById(Long id) {
        return workoutSessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("운동 기록을 찾을 수 없습니다. ID: " + id))
                .toDto();
    }

    @Override
    public List<SetRecordResponse> getSetRecordsByWorkoutSession(Long id) {
        return setRecordRepository.findAllByWorkoutSessionId(id)
                .stream()
                .map(SetRecord::toDto)
                .toList();
    }
}
