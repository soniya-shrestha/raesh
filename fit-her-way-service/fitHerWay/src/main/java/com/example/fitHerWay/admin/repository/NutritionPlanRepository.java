package com.example.fitHerWay.admin.repository;

import com.example.fitHerWay.admin.entity.BmiLevel;
import com.example.fitHerWay.admin.entity.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionPlanRepository extends JpaRepository<NutritionPlan, Long> {
    List<NutritionPlan> findByStatusTrueAndBmiLevelAndGoalType(BmiLevel bmiLevel, String goalType);
}
