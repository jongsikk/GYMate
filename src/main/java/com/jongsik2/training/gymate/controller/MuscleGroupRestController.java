package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.MuscleGroupResponse;
import com.jongsik2.training.gymate.service.MuscleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/muscle-group")
@RequiredArgsConstructor
public class MuscleGroupRestController {
    private final MuscleGroupService muscleGroupService;

    @GetMapping("/{bodyTypeId}")
    public ResponseEntity<List<MuscleGroupResponse>> getMuscleGroup(@PathVariable Long bodyTypeId) {
        return ResponseEntity.ok(muscleGroupService.getMuscleGroupByBodyTypeId(bodyTypeId));
    }
}
