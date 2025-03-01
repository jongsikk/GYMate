package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    List<WorkoutSession> findAllByUserId(Long userId);

    @Query("SELECT w.user.id, SUM(w.totalTime) as totalWorkoutTime " +
            "FROM WorkoutSession w " +
            "WHERE w.startTime >= :startOfMonth " +
            "AND w.startTime <= :endOfMonth " +
            "GROUP BY w.user.id " +
            "ORDER BY totalWorkoutTime DESC")
    List<Object[]> findTopUsersByWorkoutTime(@Param("startOfMonth") LocalDateTime startOfMonth, @Param("endOfMonth") LocalDateTime endOfMonth);
}
