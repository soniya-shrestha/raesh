package com.example.fitHerWay.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "nutrition_plans")
@NoArgsConstructor
@AllArgsConstructor
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String type;
    private String imageUrl;
    private int caloriesPerDay;

    private boolean status;

    @Enumerated(EnumType.STRING)
    private BmiLevel bmiLevel;

    @Column(name = "goal_type")
    private String goalType; // e.g., Slim, Gain Muscle, Weight Loss

    private String createdBy;
    private LocalDateTime createdAt;
}

