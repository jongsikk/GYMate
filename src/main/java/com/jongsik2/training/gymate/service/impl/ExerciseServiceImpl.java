package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.Exercise;
import com.jongsik2.training.gymate.domain.ExerciseMuscleGroup;
import com.jongsik2.training.gymate.dto.ExerciseResponse;
import com.jongsik2.training.gymate.repository.ExerciseMuscleGroupRepository;
import com.jongsik2.training.gymate.repository.ExerciseRepository;
import com.jongsik2.training.gymate.service.ExerciseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMuscleGroupRepository exerciseMuscleGroupRepository;

    @Override
    public List<ExerciseResponse> getExerciseByMuscleGroupId(Long muscleGroupId) {
        return exerciseMuscleGroupRepository.findExerciseNameByMuscleGroupId(muscleGroupId)
                .stream()
                .map(ExerciseMuscleGroup::toDto)
                .toList();
    }

    @Override
    public ExerciseResponse getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() ->
                        new EntityNotFoundException("해당 운동을 찾을 수 없습니다. ID: " + exerciseId))
                .toDto();
    }

    @Override
    public Exercise getExerciseByExerciseId(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() ->
                        new EntityNotFoundException("해당 운동을 찾을 수 없습니다. ID: " + exerciseId));
    }
}
