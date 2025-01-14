package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    List<MuscleGroup> findMuscleGroupByBodyTypeId(Long bodyTypeId);
}
