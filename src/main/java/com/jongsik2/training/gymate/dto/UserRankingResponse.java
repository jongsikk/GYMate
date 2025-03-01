package com.jongsik2.training.gymate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRankingResponse {
    private Long rank;
    private String username;
    private String totalWorkoutTime;
}
