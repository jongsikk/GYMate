package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.ExerciseResponse;
import com.jongsik2.training.gymate.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseRestController {
    private final ExerciseService exerciseService;

    @GetMapping("/{muscleGroupId}")
    public ResponseEntity<List<ExerciseResponse>> getExercises(@PathVariable Long muscleGroupId) {
        return ResponseEntity.ok(exerciseService.getExerciseByMuscleGroupId(muscleGroupId));
    }
}
