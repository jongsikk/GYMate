package com.jongsik2.training.gymate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuscleGroupResponse {
    private Long id;
    private String bodyTypeName;
    private String name;
}
