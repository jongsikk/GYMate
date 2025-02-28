package com.jongsik2.training.gymate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class WorkoutRequest {
    private Long exerciseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalWorkoutTime;
    private Integer totalRestTime;
    private List<SetRecordRequest> sets;
}
