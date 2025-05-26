package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgeGroupRepository extends JpaRepository<AgeGroup, Long> {
    Optional<AgeGroup> findByLabel(String label);
}
