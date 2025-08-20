package com.example.fitHerWay.admin.service;

import com.example.fitHerWay.admin.dto.NutritionPlanRequest;
import com.example.fitHerWay.admin.dto.NutritionPlanResponse;
import com.example.fitHerWay.admin.entity.BmiLevel;

import java.util.List;

public interface NutritionPlanService {
    NutritionPlanResponse create(NutritionPlanRequest request);
    NutritionPlanResponse update(Long id, NutritionPlanRequest request);
    void delete(Long id);
    List<NutritionPlanResponse> getAll();
    NutritionPlanResponse getById(Long id);
    List<NutritionPlanResponse> getUserPlans(BmiLevel bmiLevel, String goalType);
}
