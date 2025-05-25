package com.example.fitHerWay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class NutritionPlanDTO {
    private Long id;
    private String title;
    private String description;
    private String type;

    public NutritionPlanDTO() {}

    public NutritionPlanDTO(Long id, String title, String description, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}