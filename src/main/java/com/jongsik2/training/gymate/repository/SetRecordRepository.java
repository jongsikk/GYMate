package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.SetRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRecordRepository extends JpaRepository<SetRecord, Long> {
    List<SetRecord> findAllByWorkoutSessionId(Long workoutSessionId);
}
