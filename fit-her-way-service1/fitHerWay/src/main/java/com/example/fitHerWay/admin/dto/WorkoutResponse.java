package com.example.fitHerWay.admin.dto;

import com.example.fitHerWay.admin.entity.WorkoutPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponse {
    private Long id;
    private String workoutName;
    private String description;
    private URI workoutVideo;
    private String difficulty;
    private boolean status;
    private double minBmiLevel; // Minimum BMI level for the workout
    private double maxBmiLevel; // Maximum BMI level for the workout
    private List<String> targetedMuscles; // Comma-separated list of targeted muscles
    private int reps; // Repetitions per set
    private int sets; // Number of sets
    private int restBetweenSetsInSeconds; // Rest time in seconds
    private boolean isEquipmentRequired; // true if equipment is needed
    private String equipmentType; // e.g., "Dumbbells", "Resistance Band", "None"
    private String goalType; // e.g., Slim, Gain Muscle, Weight Loss
    private List<String> tags; // Optional: Tags or labels for filtering or search
    private int caloriesBurnedEstimate; // Optional: Calories burned estimate
    private String bmiLevel; // e.g., "Normal", "Overweight", "Underweight"

    public WorkoutResponse(WorkoutPlan savedPlan) {
        this.id = savedPlan.getId();
        this.workoutName = savedPlan.getWorkoutName();
        this.description = savedPlan.getDescription();
        // Prepend the base URL to the workout video path
        String baseUrl = "http://localhost:8483/files/"; // Replace with your actual base URL
        this.workoutVideo = URI.create(baseUrl + savedPlan.getWorkoutVideo());
        this.difficulty = savedPlan.getDifficulty();
        this.status = savedPlan.isStatus();
        this.minBmiLevel = savedPlan.getMinBmiLevel();
        this.maxBmiLevel = savedPlan.getMaxBmiLevel();
        this.targetedMuscles = savedPlan.getTargetedMuscles();
        this.reps = savedPlan.getReps();
        this.sets = savedPlan.getSets();
        this.restBetweenSetsInSeconds = savedPlan.getRestBetweenSetsInSeconds();
        this.isEquipmentRequired = savedPlan.isEquipmentRequired();
        this.equipmentType = savedPlan.getEquipmentType();
        this.goalType = savedPlan.getGoalType();
        this.tags = savedPlan.getTags();
        this.caloriesBurnedEstimate = savedPlan.getCaloriesBurnedEstimate();
        this.bmiLevel = savedPlan.getBmiLevel().name(); // Convert enum to string
    }
}
