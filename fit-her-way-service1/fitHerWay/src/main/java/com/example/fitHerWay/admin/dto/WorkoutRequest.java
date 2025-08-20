package com.example.fitHerWay.admin.dto;

import com.example.fitHerWay.admin.entity.BmiLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutRequest {
    private String workoutName;
    private String description;
    private MultipartFile workoutVideo;
    private String difficulty;
    private boolean status;
    private String goalType; // e.g., Slim, Gain Muscle, Weight Loss
    private double minBmiLevel; // Minimum BMI level for the workout
    private double maxBmiLevel; // Maximum BMI level for the workout
    private List<String> targetedMuscles; // Comma-separated list of targeted muscles
    private int reps; // Repetitions per set
    private int sets; // Number of sets
    private int restBetweenSetsInSeconds; // Rest time in seconds
    private boolean isEquipmentRequired; // true if equipment is needed
    private String equipmentType; // e.g., "Dumbbells", "Resistance Band", "None"
    private List<String> tags; // Optional: Tags or labels for filtering or search
    private int caloriesBurnedEstimate; // Optional: Calories burned estimate
    private BmiLevel bmiLevel;

}
