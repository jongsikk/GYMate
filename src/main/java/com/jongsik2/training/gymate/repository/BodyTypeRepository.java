package com.jongsik2.training.gymate.repository;

import com.jongsik2.training.gymate.domain.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    
}
