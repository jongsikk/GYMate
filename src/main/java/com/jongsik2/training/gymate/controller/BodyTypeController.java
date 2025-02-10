package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import com.jongsik2.training.gymate.service.BodyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BodyTypeController {
    private final BodyTypeService bodyTypeService;

    @GetMapping("/exercise-selection")
    public String exerciseSelection(Model model) {
        List<BodyTypeResponse> bodyTypes = bodyTypeService.getBodyTypeList();
        model.addAttribute("bodyTypes", bodyTypes);

        return "exercise-select";
    }
}
