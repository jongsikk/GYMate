package com.jongsik2.training.gymate.dto;

import com.jongsik2.training.gymate.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponse {
    private Long id;
    private String muscleGroupName;
    private String name;

    public Exercise toEntity() {
        return Exercise.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
