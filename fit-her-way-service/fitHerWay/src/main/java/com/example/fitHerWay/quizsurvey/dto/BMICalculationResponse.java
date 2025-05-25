package com.example.fitHerWay.quizsurvey.dto;

import lombok.Data;

@Data
public class BMICalculationResponse {
    private double bmi;
    private String level;
    private String message;

    public BMICalculationResponse(double bmi, String level, String message) {
        this.bmi = bmi;
        this.level = level;
        this.message = message;
    }

    // Getters and Setters
}