package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    List<WorkoutSession> findAllByUserId(Long userId);
}
