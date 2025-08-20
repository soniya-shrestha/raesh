package com.example.fitHerWay.quizsurvey.dto;


import lombok.Data;

@Data
public class BMICalculationRequest {
    private Double weight;
    private String weightUnit;  // "kg" or "lb"

    private Double height;      // used if heightUnit is "cm"
    private String heightUnit;  // "cm" or "ft"

    private Integer heightFeet; // used if heightUnit is "ft"
    private Integer heightInch;

}
