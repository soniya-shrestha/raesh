package com.example.fitHerWay.admin.controller;

import com.example.fitHerWay.admin.dto.WorkoutRequest;
import com.example.fitHerWay.admin.service.WorkoutService;
import com.example.fitHerWay.global.BaseController;
import com.example.fitHerWay.global.GlobalApiResponse;
import com.example.fitHerWay.users.message.UserSwaggerDocumentationMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/workouts")
public class WorkoutController extends BaseController {
    private final WorkoutService workoutService;



    @Operation(summary = "Create Workout by the super admin",
            description = "Workout is created by the super admin of the system")
    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createWorkout(@ModelAttribute WorkoutRequest workoutRequest) {
        return successResponse(workoutService.createWorkout(workoutRequest), "Workout created successfully");
    }

    @Operation(summary = "Get All Workouts",
            description = "Retrieve all workout plans")
    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAllWorkouts() {
        return successResponse(workoutService.getAllWorkouts(), "All workouts retrieved successfully");
    }

    @Operation(summary = "Delete Workout",
            description = "Delete a workout plan by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> deleteWorkout(@PathVariable Long id) {
        return successResponse(workoutService.deleteWorkout(id), "Workout deleted successfully");
    }

    @Operation(summary = "Update Workout", description = "Update an existing workout plan")
    @PutMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> updateWorkout(@PathVariable Long id, @ModelAttribute WorkoutRequest workoutRequest) {
        return successResponse(workoutService.updateWorkout(id,workoutRequest), "Workout updated successfully");
    }

    @Operation(summary = "Get BmiLevel enum data",
            description = "Retrieve all BmiLevel enum data")
    @GetMapping("/bmi-levels")
    public ResponseEntity<GlobalApiResponse> getBmiLevels() {
        return successResponse(workoutService.getBmiLevels(), "Bmi levels retrieved successfully");
    }

    @Operation(summary = "Get User Workouts using rule-based algorithm",
            description = "Retrieve user workouts using rule-based algorithm")
    @GetMapping("/getUserWorkouts")
    public ResponseEntity<GlobalApiResponse> getUserWorkouts() {
        return successResponse(workoutService.getUserWorkouts(), "User workouts retrieved successfully");
    }

    @GetMapping("/getById")
    public ResponseEntity<GlobalApiResponse> getWorkoutById(@RequestParam Long id) {
        return successResponse(workoutService.getWorkoutById(id), "Workout retrieved successfully");
    }


    @Operation(summary = "Mark Workout as Completed",
            description = "Mark a workout as completed for the logged-in user")
    @PostMapping("/markCompleted/{id}")
    public ResponseEntity<GlobalApiResponse> markWorkoutCompleted(@PathVariable Long id) {
        workoutService.markWorkoutCompleted(id);
        return successResponse(null, "Workout marked as completed successfully");
    }

    @Operation(summary = "Get All User Workout Progress",
            description = "Retrieve all workout progress for the logged-in user, grouped by day of the week")
    @GetMapping("/getAllProgress")
    public ResponseEntity<GlobalApiResponse> getAllProgress() {
        return successResponse(workoutService.getAllProgress(), "User workout progress retrieved successfully");
    }
}
