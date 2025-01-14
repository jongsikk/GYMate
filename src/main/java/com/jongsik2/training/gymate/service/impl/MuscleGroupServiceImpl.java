package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.MuscleGroup;
import com.jongsik2.training.gymate.dto.MuscleGroupResponse;
import com.jongsik2.training.gymate.repository.MuscleGroupRepository;
import com.jongsik2.training.gymate.service.MuscleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuscleGroupServiceImpl implements MuscleGroupService {
    private final MuscleGroupRepository muscleGroupRepository;

    @Override
    public List<MuscleGroupResponse> getMuscleGroupByBodyTypeId(Long bodyTypeId) {
        return muscleGroupRepository.findMuscleGroupByBodyTypeId(bodyTypeId)
                .stream()
                .map(MuscleGroup::toDto)
                .toList();
    }
}
