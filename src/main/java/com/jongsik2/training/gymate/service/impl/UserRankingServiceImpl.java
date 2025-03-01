package com.jongsik2.training.gymate.service.impl;

import com.jongsik2.training.gymate.domain.User;
import com.jongsik2.training.gymate.dto.UserRankingResponse;
import com.jongsik2.training.gymate.repository.UserRepository;
import com.jongsik2.training.gymate.repository.WorkoutSessionRepository;
import com.jongsik2.training.gymate.service.UserRankingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRankingServiceImpl implements UserRankingService {
    private final UserRepository userRepository;
    private final WorkoutSessionRepository workoutSessionRepository;

    @Override
    public List<UserRankingResponse> getUserRanking() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = LocalDateTime.now();
        List<Object[]> rankingData = workoutSessionRepository.findTopUsersByWorkoutTime(startOfMonth, endOfMonth);

        List<UserRankingResponse> rankings = new ArrayList<>();
        long rank = 1L;

        for (Object[] row : rankingData) {
            Long userId = (Long) row[0];
            Long totalWorkoutTime = ((Number) row[1]).longValue();
            String time = LocalTime.ofSecondOfDay(totalWorkoutTime).format(DateTimeFormatter.ofPattern("H:mm:ss"));
            
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            rankings.add(new UserRankingResponse(rank++, user.getUsername(), time));
        }

        return rankings;
    }
}
