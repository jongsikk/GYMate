package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.ExerciseMuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseMuscleGroupRepository extends JpaRepository<ExerciseMuscleGroup, Long> {
    List<ExerciseMuscleGroup> findExerciseNameByMuscleGroupId(Long muscleGroupId);
}
