package com.jongsik2.training.gymate.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSessionResponse {
    private Long id;
    private String exerciseName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalTime;
    private int totalRestTime;
}