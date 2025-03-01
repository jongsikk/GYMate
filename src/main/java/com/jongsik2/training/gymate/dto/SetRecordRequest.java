package com.jongsik2.training.gymate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetRecordRequest {
    private int setNumber;
    private int workoutTime;
    private int reps;
    private int weight;
}
