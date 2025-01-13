package com.jongsik2.training.gymate.domain;

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
    private Exercise exerciseId;
}
