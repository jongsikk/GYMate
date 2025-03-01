package com.jongsik2.training.gymate.domain;

import com.jongsik2.training.gymate.dto.SetRecordResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
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

    @Column(name = "workout_time")
    private int workoutTime;

    private int reps;

    private int weight;

    public SetRecordResponse toDto() {
        return SetRecordResponse.builder()
                .setNumber(this.setNumber)
                .workoutTime(this.workoutTime)
                .weight(this.weight)
                .reps(this.reps)
                .build();
    }
}
