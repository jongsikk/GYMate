package com.jongsik2.training.gymate.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetRecordResponse {
    private int setNumber;
    private int workoutTime;
    private int reps;
    private int weight;
}
