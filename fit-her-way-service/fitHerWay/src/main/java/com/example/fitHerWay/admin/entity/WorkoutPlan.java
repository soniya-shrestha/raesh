package com.example.fitHerWay.admin.entity;

import com.example.fitHerWay.global.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlan extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workoutName;
    private String description;
    private String workoutVideo;
    private String difficulty;
    //private String duration;
    private boolean status;

    // Target BMI level this workout is suitable for (e.g., 18.5–24.9)
    private double minBmiLevel;
    private double maxBmiLevel;

    // Muscles targeted (stored as a simple list of strings)
    @ElementCollection
    private List<String> targetedMuscles;   // e.g., ["Chest", "Triceps", "Shoulders"]

    private int reps;                       // Repetitions per set
    private int sets;                       // Number of sets
    private int restBetweenSetsInSeconds;   // Rest time in seconds

    private boolean isEquipmentRequired;    // true if equipment is needed
    private String equipmentType;           // e.g., "Dumbbells", "Resistance Band", "None"

    //private String goal;                    // e.g., "Weight Loss", "Muscle Gain", "Flexibility"

    // Optional: Tags or labels for filtering or search
    @ElementCollection
    private List<String> tags;

    // Optional: Calories burned estimate
    private int caloriesBurnedEstimate;

    @Enumerated(EnumType.STRING)
    private BmiLevel bmiLevel;

    @Column(name = "goal_type")
    private String goalType; // e.g., Slim, Gain Muscle, Weight Loss
}

