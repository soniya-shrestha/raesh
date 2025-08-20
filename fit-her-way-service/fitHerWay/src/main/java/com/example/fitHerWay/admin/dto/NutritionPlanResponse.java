package com.example.fitHerWay.admin.dto;

import com.example.fitHerWay.admin.entity.BmiLevel;
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
}
