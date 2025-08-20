package com.example.fitHerWay.admin.dto;

import com.example.fitHerWay.admin.entity.BmiLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionPlanRequest {
    private String title;
    private String description;
    private String type;
    private int caloriesPerDay;
    private BmiLevel bmiLevel;
    private String goalType;
    private boolean status;
    private String createdBy;
    private MultipartFile imageFile;
}
