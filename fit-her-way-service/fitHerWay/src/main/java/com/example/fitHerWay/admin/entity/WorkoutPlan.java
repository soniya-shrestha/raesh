package com.example.fitHerWay.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WorkoutPlan {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String difficulty;
    private String duration;
    private boolean active;
}