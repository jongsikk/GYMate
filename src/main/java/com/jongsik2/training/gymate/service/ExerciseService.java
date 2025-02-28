package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.domain.Exercise;
import com.jongsik2.training.gymate.dto.ExerciseResponse;

import java.util.List;

public interface ExerciseService {
    List<ExerciseResponse> getExerciseByMuscleGroupId(Long muscleGroupId);

    ExerciseResponse getExerciseById(Long exerciseId);

    Exercise getExerciseByExerciseId(Long exerciseId);
}
