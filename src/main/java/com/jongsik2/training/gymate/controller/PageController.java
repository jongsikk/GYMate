package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import com.jongsik2.training.gymate.dto.ExerciseSelection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Controller
public class PageController {
    @GetMapping("/")
    public String index(Model model) {

        return "main";
    }

    @GetMapping("/exercise-selection")
    public String exerciseSelection(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BodyTypeResponse>> response = restTemplate.exchange(
                "http://localhost:8080/api/body-type",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<BodyTypeResponse>>() {
                });

        model.addAttribute("bodyTypes", response.getBody());

        return "exercise-select";
    }

    @PostMapping("/set-selection")
    public String setSelection(@ModelAttribute ExerciseSelection exerciseSelection, Model model) {
        log.info(exerciseSelection.getExercise() + " " + exerciseSelection.getBodyType() + " " + exerciseSelection.getMuscleGroup());
        return "set-select";
    }
}
