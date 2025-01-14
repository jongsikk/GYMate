package com.jongsik2.training.gymate.service;

import com.jongsik2.training.gymate.dto.MuscleGroupResponse;

import java.util.List;

public interface MuscleGroupService {
    List<MuscleGroupResponse> getMuscleGroupByBodyTypeId(Long bodyTypeId);
}
