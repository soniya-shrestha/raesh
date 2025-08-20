package com.example.fitHerWay.admin.service;

import com.example.fitHerWay.admin.dto.BmiLevelResponse;
import com.example.fitHerWay.admin.dto.UserWorkoutProgressResponse;
import com.example.fitHerWay.admin.dto.WorkoutRequest;
import com.example.fitHerWay.admin.dto.WorkoutResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WorkoutService {
    WorkoutResponse createWorkout(WorkoutRequest workoutRequest);

    List<WorkoutResponse> getAllWorkouts();

    String deleteWorkout(Long id);

    WorkoutResponse updateWorkout(Long id, WorkoutRequest workoutRequest);

    List<BmiLevelResponse> getBmiLevels();

    List<WorkoutResponse> getUserWorkouts();

    WorkoutResponse getWorkoutById(Long id);

    void markWorkoutCompleted(Long id);

    Map<String, List<UserWorkoutProgressResponse>> getAllProgress();
}
