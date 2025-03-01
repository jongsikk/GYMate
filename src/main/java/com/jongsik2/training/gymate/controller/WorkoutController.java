package com.jongsik2.training.gymate.controller;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.*;
import com.jongsik2.training.gymate.service.ExerciseService;
import com.jongsik2.training.gymate.service.UserRankingService;
import com.jongsik2.training.gymate.service.UserService;
import com.jongsik2.training.gymate.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WorkoutController {
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final UserRankingService userRankingService;
    private final WorkoutSessionService workoutSessionService;

    @PostMapping("/set-selection")
    public String setSelection(@ModelAttribute ExerciseSelection exerciseSelection, Model model) {
        log.info(exerciseSelection.getExerciseId() + " " + exerciseSelection.getBodyType() + " " + exerciseSelection.getMuscleGroup());
        model.addAttribute("exerciseId", exerciseSelection.getExerciseId());
        return "set-select";
    }

    @PostMapping("/start/{exerciseId}")
    public String startExercise(@ModelAttribute ExerciseSetting exerciseSetting, @PathVariable Long exerciseId, Model model) {
        ExerciseResponse exercise = exerciseService.getExerciseById(exerciseId);
        LocalDateTime workoutStartTime = LocalDateTime.now();

        model.addAttribute("workoutStartTime", workoutStartTime);
        model.addAttribute("setting", exerciseSetting);
        model.addAttribute("exercise", exercise);

        return "exercise";
    }

    @PostMapping("/save-workout")
    public String saveWorkout(@ModelAttribute WorkoutRequest workoutRequest, RedirectAttributes redirectAttributes) {
        LocalDateTime workoutEndTime = LocalDateTime.now();
        for (SetRecordRequest s : workoutRequest.getSets()) {
            log.info(s.getReps() + " " + s.getWeight());
        }
        workoutRequest.setEndTime(workoutEndTime);

        boolean isSuccess = workoutSessionService.save(userService.getCurrentUser(), workoutRequest);

        if (isSuccess) {
            redirectAttributes.addFlashAttribute("message", "운동 기록이 성공적으로 저장되었습니다.");
        }

        return "redirect:/workout-history";
    }

    @GetMapping("/workout-history")
    public String getWorkoutHistory(Model model) {
        User currentUser = userService.getCurrentUser();
        List<WorkoutSessionResponse> workoutSessions = workoutSessionService.getWorkoutHistoryByUserId(currentUser.getId());
        model.addAttribute("workoutSessions", workoutSessions);

        return "workout-history";
    }

    @GetMapping("/workout-history/{id}")
    public String getWorkoutDetail(@PathVariable Long id, Model model) {
        WorkoutSessionResponse workoutSession = workoutSessionService.getWorkoutSessionById(id);
        List<SetRecordResponse> setRecords = workoutSessionService.getSetRecordsByWorkoutSession(id);

        model.addAttribute("workoutSession", workoutSession);
        model.addAttribute("setRecords", setRecords);

        return "workout-detail";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        List<UserRankingResponse> rankings = userRankingService.getUserRanking();
        model.addAttribute("rankings", rankings);
        
        return "ranking";
    }
}
