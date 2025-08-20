//package com.example.fitHerWay.admin.controller;
//
//import com.example.fitHerWay.admin.entity.NutritionPlan;
//import com.example.fitHerWay.admin.entity.WorkoutPlan;
//import com.example.fitHerWay.admin.service.NutritionPlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
//public class AdminController {
//    @Autowired
//    private UserService userService;
//    @Autowired private WorkoutPlanService workoutService;
//    @Autowired private NutritionPlanService nutritionService;
//    @Autowired private ProgressService progressService;
//
//    // User Management
//    @GetMapping("/users")
//    public List<UserDto> getAllUsers() { return userService.getAllUsers(); }
//
//    @PutMapping("/users/{id}/status")
//    public void updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
//        userService.updateUserStatus(id, active);
//    }
//
//    // Workout Management
//    @PostMapping("/workouts") public WorkoutPlan addWorkout(@RequestBody WorkoutPlan plan) { return workoutService.save(plan); }
//    @PutMapping("/workouts/{id}") public WorkoutPlan editWorkout(@PathVariable Long id, @RequestBody WorkoutPlan plan) { return workoutService.update(id, plan); }
//    @DeleteMapping("/workouts/{id}") public void deleteWorkout(@PathVariable Long id) { workoutService.delete(id); }
//
//    // Nutrition Management
//    @PostMapping("/nutrition") public NutritionPlan addNutrition(@RequestBody NutritionPlan plan) { return nutritionService.save(plan); }
//    @PutMapping("/nutrition/{id}") public NutritionPlan editNutrition(@PathVariable Long id, @RequestBody NutritionPlan plan) { return nutritionService.update(id, plan); }
//    @DeleteMapping("/nutrition/{id}") public void deleteNutrition(@PathVariable Long id) { nutritionService.delete(id); }
//
////    // Progress Monitoring
////    @GetMapping("/progress")
////    public List<ProgressDto> getUserProgress() { return progressService.getAllProgress(); }
//}
