package com.jongsik2.training.gymate.domain;

import com.jongsik2.training.gymate.dto.ExerciseResponse;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "exercise_muscle_groups")
public class ExerciseMuscleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "muscle_group_id")
    private MuscleGroup muscleGroup;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public ExerciseResponse toDto() {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .muscleGroupName(muscleGroup.getName())
                .name(exercise.getName())
                .build();
    }
}
