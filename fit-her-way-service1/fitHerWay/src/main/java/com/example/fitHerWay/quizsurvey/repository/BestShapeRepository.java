package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.BestShape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BestShapeRepository extends JpaRepository<BestShape,Long> {

    Optional<BestShape> findByLabel(String bestShape);
}
