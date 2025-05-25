package com.example.fitHerWay.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "nutrition_plans")
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String type;
}

