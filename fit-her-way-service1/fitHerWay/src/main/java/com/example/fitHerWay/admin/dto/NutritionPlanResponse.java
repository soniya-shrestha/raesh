package com.example.fitHerWay.admin.dto;

import com.example.fitHerWay.admin.entity.BmiLevel;
import com.example.fitHerWay.admin.entity.NutritionPlan;
import com.example.fitHerWay.utils.file.FileUrlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionPlanResponse {
    private Long id;
    private String title;
    private String description;
    private String type;
    private int caloriesPerDay;
    private BmiLevel bmiLevel;
    private String goalType;
    private boolean status;
    private URI imageUrl;


    public NutritionPlanResponse(NutritionPlan nutritionPlan) {
        this.id = nutritionPlan.getId();
        this.title = nutritionPlan.getTitle();
        this.description = nutritionPlan.getDescription();
        this.type = nutritionPlan.getType();
        this.caloriesPerDay = nutritionPlan.getCaloriesPerDay();
        this.bmiLevel = nutritionPlan.getBmiLevel();
        this.goalType = nutritionPlan.getGoalType();
        this.status = nutritionPlan.isStatus();
        this.imageUrl = FileUrlUtil.getFileUri(nutritionPlan.getImageUrl());
    }
}
