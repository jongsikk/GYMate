package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import com.jongsik2.training.gymate.service.BodyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/body-type")
@RequiredArgsConstructor
public class BodyTypeRestController {
    private final BodyTypeService bodyTypeService;

    @GetMapping
    public ResponseEntity<List<BodyTypeResponse>> getBodyTypeList() {
        return ResponseEntity.ok(bodyTypeService.getBodyTypeList());
    }
}
