package com.jongsik2.training.gymate.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "set_record")
public class SetRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;

    @Column(name = "set_number")
    private int setNumber;

    private int weight;

    private int reps;

    @Column(name = "rest_time")
    private int restTime;
}
