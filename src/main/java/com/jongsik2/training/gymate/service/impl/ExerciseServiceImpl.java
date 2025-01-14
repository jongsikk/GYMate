package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.ExerciseMuscleGroup;
import com.jongsik2.training.gymate.dto.ExerciseResponse;
import com.jongsik2.training.gymate.repository.ExerciseMuscleGroupRepository;
import com.jongsik2.training.gymate.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseMuscleGroupRepository exerciseMuscleGroupRepository;

    @Override
    public List<ExerciseResponse> getExerciseByMuscleGroupId(Long muscleGroupId) {
        return exerciseMuscleGroupRepository.findExerciseNameByMuscleGroupId(muscleGroupId)
                .stream()
                .map(ExerciseMuscleGroup::toDto)
                .toList();
    }
}
