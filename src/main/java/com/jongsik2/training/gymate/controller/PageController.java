package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.dto.BodyTypeResponse;
import com.jongsik2.training.gymate.dto.ExerciseSelection;
import com.jongsik2.training.gymate.dto.ExerciseSetting;
import com.jongsik2.training.gymate.dto.SignUpRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class PageController {
    @GetMapping("/")
    public String index(Model model) {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignUpRequest signUpRequest, RedirectAttributes redirectAttributes) {
        log.info("doSignup: " + signUpRequest.getEmail() + " " + signUpRequest.getPassword() + " " + signUpRequest.getUsername());
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "redirect:/login";
    }

    @GetMapping("/exercise-selection")
    public String exerciseSelection(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BodyTypeResponse>> response = restTemplate.exchange(
                "http://localhost:8080/api/body-type",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<BodyTypeResponse>>() {
                }
        );

        model.addAttribute("bodyTypes", response.getBody());

        return "exercise-select";
    }

    @PostMapping("/set-selection")
    public String setSelection(@ModelAttribute ExerciseSelection exerciseSelection, Model model) {
        log.info(exerciseSelection.getExercise() + " " + exerciseSelection.getBodyType() + " " + exerciseSelection.getMuscleGroup());
        return "set-select";
    }

    @PostMapping("/start")
    public String startExercise(@ModelAttribute ExerciseSetting exerciseSetting, Model model) {
        log.info(exerciseSetting.getSets() + " " + exerciseSetting.getReps() + " " + exerciseSetting.getRestTime());
        model.addAttribute("setting", exerciseSetting);
        return "exercise";
    }
}
