package com.example.fitHerWay.quizsurvey.service;

import com.example.fitHerWay.quizsurvey.dto.BMICalculationRequest;
import com.example.fitHerWay.quizsurvey.dto.BMICalculationResponse;
import org.springframework.stereotype.Service;

@Service
public class BMIService {

    public BMICalculationResponse calculateBMI(BMICalculationRequest req) {
        Double weight = req.getWeight();
        if (weight == null) throw new IllegalArgumentException("Weight is required");

        // Convert weight to kg if necessary
        if ("lb".equalsIgnoreCase(req.getWeightUnit())) {
            weight = weight * 0.453592;
        }

        double heightInMeters;
        if ("cm".equalsIgnoreCase(req.getHeightUnit())) {
            if (req.getHeight() == null || req.getHeight() <= 0) {
                throw new IllegalArgumentException("Valid height in cm is required.");
            }
            heightInMeters = req.getHeight() / 100.0;
        } else if ("ft".equalsIgnoreCase(req.getHeightUnit())) {
            int feet = req.getHeightFeet() != null ? req.getHeightFeet() : 0;
            int inches = req.getHeightInch() != null ? req.getHeightInch() : 0;
            if (feet == 0 && inches == 0) {
                throw new IllegalArgumentException("Height in ft/in is required.");
            }
            heightInMeters = (feet * 12 + inches) * 0.0254;
        } else {
            throw new IllegalArgumentException("Invalid height unit.");
        }

        if (heightInMeters <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero.");
        }

        double bmi = weight / (heightInMeters * heightInMeters);
        double roundedBmi = Math.round(bmi * 10.0) / 10.0;

        String level;
        String message;

        if (bmi < 18.5) {
            level = "underweight";
            message = "Your BMI is " + roundedBmi + ", which is considered underweight. Let's work on improving your health with a personalized plan.";
        } else if (bmi < 24.9) {
            level = "healthy";
            message = "Your BMI is " + roundedBmi + ", which is considered normal. You are doing a great job. We will tailor a personal plan for your needs.";
        } else if (bmi < 30) {
            level = "overweight";
            message = "Your BMI is " + roundedBmi + ", which is considered overweight. A custom plan will help you achieve a healthy weight.";
        } else {
            level = "obese";
            message = "Your BMI is " + roundedBmi + ", which falls in the obese range. Let’s build a plan to help you reach a healthier range.";
        }

        return new BMICalculationResponse(roundedBmi, level, message);
    }
}
