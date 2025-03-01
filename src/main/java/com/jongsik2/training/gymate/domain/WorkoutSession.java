package com.jongsik2.training.gymate.domain;


import com.jongsik2.training.gymate.dto.WorkoutSessionResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "workout_sessions")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "total_rest_time")
    private int totalRestTime;

    public WorkoutSessionResponse toDto() {
        return WorkoutSessionResponse.builder()
                .id(this.getId())
                .exerciseName(this.getExercise().getName())
                .startTime(this.getStartTime())
                .endTime(this.getEndTime())
                .totalTime(this.getTotalTime())
                .totalRestTime(this.getTotalRestTime())
                .build();
    }
}
