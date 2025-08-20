

//package com.example.fitHerWay.admin.service.impl;
//
//import com.example.fitHerWay.admin.dto.BmiLevelResponse;
//import com.example.fitHerWay.admin.dto.WorkoutRequest;
//import com.example.fitHerWay.admin.dto.WorkoutResponse;
//import com.example.fitHerWay.admin.entity.BmiLevel;
//import com.example.fitHerWay.admin.entity.WorkoutPlan;
//import com.example.fitHerWay.admin.repository.WorkoutPlanRepository;
//import com.example.fitHerWay.admin.service.WorkoutService;
//import com.example.fitHerWay.progress.entity.Status;
//import com.example.fitHerWay.progress.entity.UserWorkoutProgress;
//import com.example.fitHerWay.progress.repository.UserWorkoutProgressRepository;
//import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
//import com.example.fitHerWay.quizsurvey.repository.UserQuizResponseRepository;
//import com.example.fitHerWay.role.repository.RoleRepository;
//import com.example.fitHerWay.users.entity.User;
//import com.example.fitHerWay.utils.file.FileHandlerUtil;
//import com.example.fitHerWay.utils.logged_in_user.LoggedInUser;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class WorkoutServiceImpl implements WorkoutService {
//    private final WorkoutPlanRepository workoutPlanRepository;
//    private final LoggedInUser loggedInUser;
//    private final FileHandlerUtil fileHandlerUtil;
//    private final RoleRepository roleRepository;
//    private final UserQuizResponseRepository quizResponseRepository;
//    private final UserWorkoutProgressRepository userWorkoutProgressRepository;
//
//    @Override
//    public WorkoutResponse createWorkout(WorkoutRequest workoutRequest) {
//        log.info("Creating workout for user with request: {}", workoutRequest);
//        if (workoutRequest == null) {
//            throw new IllegalArgumentException("Workout request cannot be null");
//        }
//        if (workoutRequest.getWorkoutName() == null || workoutRequest.getWorkoutName().isBlank()) {
//            throw new IllegalArgumentException("Workout name cannot be null or blank");
//        }
//        if (workoutRequest.getWorkoutVideo() == null || workoutRequest.getWorkoutVideo().isEmpty()) {
//            throw new IllegalArgumentException("Workout video cannot be null or empty");
//        }
//        if (workoutRequest.getDifficulty() == null) {
//            throw new IllegalArgumentException("Workout difficulty cannot be null");
//        }
//
//        WorkoutPlan workoutPlan = new WorkoutPlan();
//        workoutPlan.setWorkoutName(workoutRequest.getWorkoutName());
//        workoutPlan.setDescription(workoutRequest.getDescription());
//        workoutPlan.setWorkoutVideo(fileHandlerUtil.saveFile(workoutRequest.getWorkoutVideo(), "workout").getFileDownloadUri());
//        workoutPlan.setDifficulty(workoutRequest.getDifficulty());
//        workoutPlan.setStatus(workoutRequest.isStatus());
//        workoutPlan.setMinBmiLevel(workoutRequest.getMinBmiLevel());
//        workoutPlan.setMaxBmiLevel(workoutRequest.getMaxBmiLevel());
//        workoutPlan.setTargetedMuscles(workoutRequest.getTargetedMuscles());
//        workoutPlan.setReps(workoutRequest.getReps());
//        workoutPlan.setSets(workoutRequest.getSets());
//        workoutPlan.setRestBetweenSetsInSeconds(workoutRequest.getRestBetweenSetsInSeconds());
//        workoutPlan.setEquipmentRequired(workoutRequest.isEquipmentRequired());
//        workoutPlan.setEquipmentType(workoutRequest.getEquipmentType());
//        workoutPlan.setTags(workoutRequest.getTags());
//        workoutPlan.setCaloriesBurnedEstimate(workoutRequest.getCaloriesBurnedEstimate());
//        workoutPlan.setBmiLevel(workoutRequest.getBmiLevel());
//        workoutPlan.setGoalType(workoutRequest.getGoalType());
//
//        WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);
//        log.info("Workout created successfully with ID: {}", savedPlan.getId());
//        return new WorkoutResponse(savedPlan);
//    }
//
//    @Override
//    public String deleteWorkout(Long id) {
//        log.info("Deleting workout with ID: {}", id);
//        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Workout not found with ID: " + id));
//        workoutPlan.setStatus(false);
//        workoutPlanRepository.save(workoutPlan);
//        log.info("Workout with ID: {} deleted successfully", id);
//        return "Workout with the ID:" + id + " deleted successfully";
//    }
//
//    @Override
//    public WorkoutResponse updateWorkout(Long id, WorkoutRequest workoutRequest) {
//        log.info("Updating workout with ID: {}", id);
//        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Workout not found with ID: " + id));
//        if (workoutRequest == null) {
//            throw new IllegalArgumentException("Workout request cannot be null");
//        }
//        if (workoutRequest.getWorkoutName() == null || workoutRequest.getWorkoutName().isBlank()) {
//            throw new IllegalArgumentException("Workout name cannot be null or blank");
//        }
//        workoutPlan.setWorkoutName(workoutRequest.getWorkoutName());
//        workoutPlan.setDescription(workoutRequest.getDescription());
//        if (workoutRequest.getWorkoutVideo() != null && !workoutRequest.getWorkoutVideo().isEmpty()) {
//            workoutPlan.setWorkoutVideo(fileHandlerUtil.saveFile(workoutRequest.getWorkoutVideo(), "workout").getFileDownloadUri());
//        }
//        workoutPlan.setDifficulty(workoutRequest.getDifficulty());
//        workoutPlan.setStatus(workoutRequest.isStatus());
//        workoutPlan.setMinBmiLevel(workoutRequest.getMinBmiLevel());
//        workoutPlan.setMaxBmiLevel(workoutRequest.getMaxBmiLevel());
//        workoutPlan.setTargetedMuscles(workoutRequest.getTargetedMuscles());
//        workoutPlan.setReps(workoutRequest.getReps());
//        workoutPlan.setSets(workoutRequest.getSets());
//        workoutPlan.setRestBetweenSetsInSeconds(workoutRequest.getRestBetweenSetsInSeconds());
//        workoutPlan.setEquipmentRequired(workoutRequest.isEquipmentRequired());
//        workoutPlan.setEquipmentType(workoutRequest.getEquipmentType());
//        workoutPlan.setTags(workoutRequest.getTags());
//        workoutPlan.setCaloriesBurnedEstimate(workoutRequest.getCaloriesBurnedEstimate());
//        workoutPlan.setBmiLevel(workoutRequest.getBmiLevel());
//        workoutPlan.setGoalType(workoutRequest.getGoalType());
//        WorkoutPlan updatedPlan = workoutPlanRepository.save(workoutPlan);
//        log.info("Workout with ID: {} updated successfully", id);
//        return new WorkoutResponse(updatedPlan);
//    }
//
//    @Override
//    public List<BmiLevelResponse> getBmiLevels() {
//        log.info("Retrieving all BMI levels");
//        return Arrays.stream(BmiLevel.values())
//                .map(bmiLevel -> new BmiLevelResponse(bmiLevel.name()))
//                .toList();
//    }
//
//    @Override
//    public List<WorkoutResponse> getAllWorkouts() {
//        log.info("Retrieving all workouts");
//        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findAllByStatus(true);
//        if (workoutPlans.isEmpty()) {
//            log.warn("No workouts found");
//            return List.of();
//        }
//        log.info("Found {} workouts", workoutPlans.size());
//        return workoutPlans.stream()
//                .map(WorkoutResponse::new)
//                .toList();
//    }
//
//    @Override
//    public List<WorkoutResponse> getUserWorkouts() {
//        log.info("Retrieving user workouts using rule-based algorithm");
//
//        // Get the logged-in user
//        User user = loggedInUser.getLoggedInUser();
//        if (user == null) {
//            throw new IllegalArgumentException("No user is currently logged in");
//        }
//
//        // Fetch the user's quiz response
//        UserQuizResponse quizResponse = quizResponseRepository.findUserQuizResponseByUser(user).orElse(null);
//        if (quizResponse == null) {
//            log.warn("No quiz response found for user ID: {}", user.getId());
//            return Collections.emptyList();
//        }
//
//        // Extract relevant data from the quiz response with defaults
//        String experience = quizResponse.getExperience() != null ? quizResponse.getExperience().getLabel().toLowerCase() : "beginner";
//        String goal = quizResponse.getGoal() != null ? quizResponse.getGoal().getLabel().toLowerCase() : "general fitness";
//        String equipment = quizResponse.getEquipment() != null ? quizResponse.getEquipment().getLabel().toLowerCase() : "none";
//        double bmi = quizResponse.getBmiResult() != null ? quizResponse.getBmiResult() : 0.0;
//        List<String> preferDays = quizResponse.getPreferDays() != null ? quizResponse.getPreferDays().stream().map(String::toLowerCase).toList() : Collections.emptyList();
//        List<String> bodyParts = quizResponse.getBodyParts() != null ? quizResponse.getBodyParts().stream().map(String::toLowerCase).toList() : Collections.emptyList();
//
//        log.info("Applying rule-based algorithm with experience: {}, goal: {}, equipment: {}, BMI: {}, preferDays: {}, bodyParts: {}",
//                experience, goal, equipment, bmi, preferDays, bodyParts);
//
//        // Retrieve all workouts with status true
//        List<WorkoutPlan> allWorkouts = workoutPlanRepository.findAllByStatus(true);
//        if (allWorkouts.isEmpty()) {
//            log.warn("No active workouts found in the database");
//            return Collections.emptyList();
//        }
//
//        // Filter workouts based on criteria
//        List<WorkoutPlan> filteredWorkouts = allWorkouts.stream()
//                .filter(workoutPlan -> {
//                    // Sanitize targetedMuscles and tags to handle JSON-like strings
//                    List<String> sanitizedMuscles = sanitizeList(workoutPlan.getTargetedMuscles());
//                    List<String> sanitizedTags = sanitizeList(workoutPlan.getTags());
//
//                    boolean bmiMatch = bmi >= workoutPlan.getMinBmiLevel() && bmi <= workoutPlan.getMaxBmiLevel();
//                    boolean goalMatch = workoutPlan.getGoalType() != null && workoutPlan.getGoalType().toLowerCase().equals(goal);
//                    boolean equipmentMatch = !workoutPlan.isEquipmentRequired() || (workoutPlan.getEquipmentType() != null && workoutPlan.getEquipmentType().toLowerCase().equals(equipment));
//                    boolean experienceMatch = workoutPlan.getDifficulty() != null && workoutPlan.getDifficulty().toLowerCase().equals(experience);
//                    boolean daysMatch = preferDays.isEmpty() || (!sanitizedTags.isEmpty() && sanitizedTags.stream()
//                            .map(String::toLowerCase)
//                            .anyMatch(preferDays::contains));
//                    boolean musclesMatch = bodyParts.isEmpty() || (!sanitizedMuscles.isEmpty() && sanitizedMuscles.stream()
//                            .map(String::toLowerCase)
//                            .anyMatch(bodyParts::contains));
//
//                    log.info("Workout ID {}: bmiMatch={}, goalMatch={}, equipmentMatch={}, experienceMatch={}, daysMatch={}, musclesMatch={}, sanitizedMuscles={}, sanitizedTags={}",
//                            workoutPlan.getId(), bmiMatch, goalMatch, equipmentMatch, experienceMatch, daysMatch, musclesMatch, sanitizedMuscles, sanitizedTags);
//
//                    return bmiMatch && goalMatch && equipmentMatch && experienceMatch && daysMatch && musclesMatch;
//                })
//                .toList();
//
//        log.info("Found {} matching workouts after filtering", filteredWorkouts.size());
//
//        // Convert to WorkoutResponse
//        return filteredWorkouts.stream()
//                .map(WorkoutResponse::new)
//                .toList();
//    }
//
//    @Override
//    public WorkoutResponse getWorkoutById(Long id) {
//        log.info("Retrieving workout by ID: {}", id);
//        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Workout not found with ID: " + id));
//        log.info("Workout with ID: {} retrieved successfully", id);
//        return new WorkoutResponse(workoutPlan);
//    }
//
//
//    public void markWorkoutCompleted(Long workoutId) {
//        log.info("Marking workout ID {} as completed for user", workoutId);
//        User user = loggedInUser.getLoggedInUser();
//        if (user == null) {
//            throw new IllegalArgumentException("No user is currently logged in");
//        }
//
//        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutId)
//                .orElseThrow(() -> new EntityNotFoundException("Workout not found with ID: " + workoutId));
//
//        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
//        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
//        List<UserWorkoutProgress> existingProgress = userWorkoutProgressRepository.findByUserAndWorkoutPlan(user, workoutPlan);
//
//        UserWorkoutProgress progress;
//        if (existingProgress.isEmpty() || existingProgress.stream().noneMatch(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))) {
//            progress = new UserWorkoutProgress();
//            progress.setUser(user);
//            progress.setWorkoutPlan(workoutPlan);
//            progress.setStatus(Status.COMPLETED);
//            progress.setCompletionDate(LocalDateTime.now());
//            userWorkoutProgressRepository.save(progress);
//            log.info("Workout ID {} marked as completed for user ID {}", workoutId, user.getId());
//        } else {
//            progress = existingProgress.stream()
//                    .filter(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalStateException("Unexpected error in progress check"));
//            progress.setStatus(Status.COMPLETED);
//            progress.setCompletionDate(LocalDateTime.now());
//            userWorkoutProgressRepository.save(progress);
//            log.info("Workout ID {} completion status updated for user ID {}", workoutId, user.getId());
//        }
//    }
//
//    // Helper method to sanitize lists that may contain JSON-like strings
//    private List<String> sanitizeList(List<String> inputList) {
//        if (inputList == null) {
//            return Collections.emptyList();
//        }
//        return inputList.stream()
//                .map(item -> item.replaceAll("[\"\\[\\]]", "").trim())
//                .filter(item -> !item.isEmpty())
//                .toList();
//    }
//
//}


package com.example.fitHerWay.admin.service.impl;

import com.example.fitHerWay.admin.dto.BmiLevelResponse;
import com.example.fitHerWay.admin.dto.UserWorkoutProgressResponse;
import com.example.fitHerWay.admin.dto.WorkoutRequest;
import com.example.fitHerWay.admin.dto.WorkoutResponse;
import com.example.fitHerWay.admin.entity.BmiLevel;
import com.example.fitHerWay.admin.entity.WorkoutPlan;
import com.example.fitHerWay.admin.repository.WorkoutPlanRepository;
import com.example.fitHerWay.admin.service.WorkoutService;
import com.example.fitHerWay.progress.entity.UserWorkoutProgress;
import com.example.fitHerWay.progress.entity.Status;
import com.example.fitHerWay.progress.repository.UserWorkoutProgressRepository;
import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import com.example.fitHerWay.quizsurvey.repository.UserQuizResponseRepository;
import com.example.fitHerWay.role.repository.RoleRepository;
import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.utils.file.FileHandlerUtil;
import com.example.fitHerWay.utils.logged_in_user.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutPlanRepository workoutPlanRepository;
    private final LoggedInUser loggedInUser;
    private final FileHandlerUtil fileHandlerUtil;
    private final RoleRepository roleRepository;
    private final UserQuizResponseRepository quizResponseRepository;
    private final UserWorkoutProgressRepository userWorkoutProgressRepository;

    @Override
    public WorkoutResponse createWorkout(WorkoutRequest workoutRequest) {
        log.info("Creating workout for user with request: {}", workoutRequest);
        if (workoutRequest == null) {
            throw new IllegalArgumentException("Workout request cannot be null");
        }
        if (workoutRequest.getWorkoutName() == null || workoutRequest.getWorkoutName().isBlank()) {
            throw new IllegalArgumentException("Workout name cannot be null or blank");
        }
        if (workoutRequest.getWorkoutVideo() == null || workoutRequest.getWorkoutVideo().isEmpty()) {
            throw new IllegalArgumentException("Workout video cannot be null or empty");
        }
        if (workoutRequest.getDifficulty() == null) {
            throw new IllegalArgumentException("Workout difficulty cannot be null");
        }

        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setWorkoutName(workoutRequest.getWorkoutName());
        workoutPlan.setDescription(workoutRequest.getDescription());
        workoutPlan.setWorkoutVideo(fileHandlerUtil.saveFile(workoutRequest.getWorkoutVideo(), "workout").getFileDownloadUri());
        workoutPlan.setDifficulty(workoutRequest.getDifficulty());
        workoutPlan.setStatus(workoutRequest.isStatus());
        workoutPlan.setMinBmiLevel(workoutRequest.getMinBmiLevel());
        workoutPlan.setMaxBmiLevel(workoutRequest.getMaxBmiLevel());
        workoutPlan.setTargetedMuscles(workoutRequest.getTargetedMuscles());
        workoutPlan.setReps(workoutRequest.getReps());
        workoutPlan.setSets(workoutRequest.getSets());
        workoutPlan.setRestBetweenSetsInSeconds(workoutRequest.getRestBetweenSetsInSeconds());
        workoutPlan.setEquipmentRequired(workoutRequest.isEquipmentRequired());
        workoutPlan.setEquipmentType(workoutRequest.getEquipmentType());
        workoutPlan.setTags(workoutRequest.getTags());
        workoutPlan.setCaloriesBurnedEstimate(workoutRequest.getCaloriesBurnedEstimate());
        workoutPlan.setBmiLevel(workoutRequest.getBmiLevel());
        workoutPlan.setGoalType(workoutRequest.getGoalType());

        WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);
        log.info("Workout created successfully with ID: {}", savedPlan.getId());
        return new WorkoutResponse(savedPlan);
    }

    @Override
    public List<WorkoutResponse> getAllWorkouts() {
        log.info("Retrieving all workouts");
        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findAllByStatus(true);
        if (workoutPlans.isEmpty()) {
            log.warn("No workouts found");
            return List.of();
        }
        log.info("Found {} workouts", workoutPlans.size());
        return workoutPlans.stream()
                .map(WorkoutResponse::new)
                .toList();
    }

    @Override
    public String deleteWorkout(Long id) {
        log.info("Deleting workout with ID: {}", id);
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found with ID: " + id));
        workoutPlan.setStatus(false);
        workoutPlanRepository.save(workoutPlan);
        log.info("Workout with ID: {} deleted successfully", id);
        return "Workout with the ID:" + id + " deleted successfully";
    }

    @Override
    public WorkoutResponse updateWorkout(Long id, WorkoutRequest workoutRequest) {
        log.info("Updating workout with ID: {}", id);
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found with ID: " + id));
        if (workoutRequest == null) {
            throw new IllegalArgumentException("Workout request cannot be null");
        }
        if (workoutRequest.getWorkoutName() == null || workoutRequest.getWorkoutName().isBlank()) {
            throw new IllegalArgumentException("Workout name cannot be null or blank");
        }
        workoutPlan.setWorkoutName(workoutRequest.getWorkoutName());
        workoutPlan.setDescription(workoutRequest.getDescription());
        if (workoutRequest.getWorkoutVideo() != null && !workoutRequest.getWorkoutVideo().isEmpty()) {
            workoutPlan.setWorkoutVideo(fileHandlerUtil.saveFile(workoutRequest.getWorkoutVideo(), "workout").getFileDownloadUri());
        }
        workoutPlan.setDifficulty(workoutRequest.getDifficulty());
        workoutPlan.setStatus(workoutRequest.isStatus());
        workoutPlan.setMinBmiLevel(workoutRequest.getMinBmiLevel());
        workoutPlan.setMaxBmiLevel(workoutRequest.getMaxBmiLevel());
        workoutPlan.setTargetedMuscles(workoutRequest.getTargetedMuscles());
        workoutPlan.setReps(workoutRequest.getReps());
        workoutPlan.setSets(workoutRequest.getSets());
        workoutPlan.setRestBetweenSetsInSeconds(workoutRequest.getRestBetweenSetsInSeconds());
        workoutPlan.setEquipmentRequired(workoutRequest.isEquipmentRequired());
        workoutPlan.setEquipmentType(workoutRequest.getEquipmentType());
        workoutPlan.setTags(workoutRequest.getTags());
        workoutPlan.setCaloriesBurnedEstimate(workoutRequest.getCaloriesBurnedEstimate());
        workoutPlan.setBmiLevel(workoutRequest.getBmiLevel());
        workoutPlan.setGoalType(workoutRequest.getGoalType());
        WorkoutPlan updatedPlan = workoutPlanRepository.save(workoutPlan);
        log.info("Workout with ID: {} updated successfully", id);
        return new WorkoutResponse(updatedPlan);
    }

    @Override
    public List<BmiLevelResponse> getBmiLevels() {
        log.info("Retrieving all BMI levels");
        return Arrays.stream(BmiLevel.values())
                .map(bmiLevel -> new BmiLevelResponse(bmiLevel.name()))
                .toList();
    }

    @Override
    public List<WorkoutResponse> getUserWorkouts() {
        log.info("Retrieving user workouts using rule-based algorithm");

        User user = loggedInUser.getLoggedInUser();
        if (user == null) {
            throw new IllegalArgumentException("No user is currently logged in");
        }

        UserQuizResponse quizResponse = quizResponseRepository.findUserQuizResponseByUser(user).orElse(null);
        if (quizResponse == null) {
            log.warn("No quiz response found for user ID: {}", user.getId());
            return Collections.emptyList();
        }

        String experience = quizResponse.getExperience() != null ? quizResponse.getExperience().getLabel().toLowerCase() : "beginner";
        String goal = quizResponse.getGoal() != null ? quizResponse.getGoal().getLabel().toLowerCase() : "general fitness";
        String equipment = quizResponse.getEquipment() != null ? quizResponse.getEquipment().getLabel().toLowerCase() : "none";
        double bmi = quizResponse.getBmiResult() != null ? quizResponse.getBmiResult() : 0.0;
        List<String> preferDays = quizResponse.getPreferDays() != null
                ? quizResponse.getPreferDays().stream().map(day -> day.toLowerCase().trim()).collect(Collectors.toList())
                : Collections.emptyList();
        List<String> bodyParts = quizResponse.getBodyParts() != null
                ? quizResponse.getBodyParts().stream().map(String::toLowerCase).collect(Collectors.toList())
                : Collections.emptyList();

        log.info("Applying rule-based algorithm with experience: {}, goal: {}, equipment: {}, BMI: {}, preferDays: {}, bodyParts: {}",
                experience, goal, equipment, bmi, preferDays, bodyParts);

        List<WorkoutPlan> allWorkouts = workoutPlanRepository.findAllByStatus(true);
        if (allWorkouts.isEmpty()) {
            log.warn("No active workouts found in the database");
            return Collections.emptyList();
        }

        // Filter workouts based on criteria, including current day (Wednesday)
        List<WorkoutPlan> filteredWorkouts = allWorkouts.stream()
                .filter(workoutPlan -> {
                    List<String> sanitizedMuscles = sanitizeList(workoutPlan.getTargetedMuscles());
                    List<String> sanitizedTags = sanitizeList(workoutPlan.getTags());

                    boolean bmiMatch = bmi >= workoutPlan.getMinBmiLevel() && bmi <= workoutPlan.getMaxBmiLevel();
                    boolean goalMatch = workoutPlan.getGoalType() != null && workoutPlan.getGoalType().toLowerCase().equals(goal);
                    boolean equipmentMatch = !workoutPlan.isEquipmentRequired() || (workoutPlan.getEquipmentType() != null && workoutPlan.getEquipmentType().toLowerCase().equals(equipment));
                    boolean experienceMatch = workoutPlan.getDifficulty() != null && workoutPlan.getDifficulty().toLowerCase().equals(experience);
                    boolean daysMatch = preferDays.isEmpty() || (!sanitizedTags.isEmpty() && sanitizedTags.stream()
                            .map(String::toLowerCase)
                            .anyMatch(preferDays::contains));
                    boolean musclesMatch = bodyParts.isEmpty() || (!sanitizedMuscles.isEmpty() && sanitizedMuscles.stream()
                            .map(String::toLowerCase)
                            .anyMatch(bodyParts::contains));

                    log.info("Workout ID {}: bmiMatch={}, goalMatch={}, equipmentMatch={}, experienceMatch={}, daysMatch={}, musclesMatch={}, sanitizedMuscles={}, sanitizedTags={}, preferDays={}",
                            workoutPlan.getId(), bmiMatch, goalMatch, equipmentMatch, experienceMatch, daysMatch, musclesMatch, sanitizedMuscles, sanitizedTags, preferDays);

                    return bmiMatch && goalMatch && equipmentMatch && experienceMatch && daysMatch && musclesMatch;
                })
                .toList();

        log.info("Found {} matching workouts after filtering", filteredWorkouts.size());

        // Save each workout to UserWorkoutProgress with IN_PROGRESS status
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        for (WorkoutPlan workoutPlan : filteredWorkouts) {
            List<UserWorkoutProgress> existingProgress = userWorkoutProgressRepository.findByUserAndWorkoutPlan(user, workoutPlan);
            UserWorkoutProgress progress;
            if (existingProgress.isEmpty() || existingProgress.stream().noneMatch(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))) {
                progress = new UserWorkoutProgress();
                progress.setUser(user);
                progress.setWorkoutPlan(workoutPlan);
                progress.setStatus(Status.IN_PROGRESS);
                progress.setCompletionDate(LocalDateTime.now());
                userWorkoutProgressRepository.save(progress);
                log.info("Workout ID {} marked as IN_PROGRESS for user ID {}", workoutPlan.getId(), user.getId());
            } else {
                progress = existingProgress.stream()
                        .filter(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Unexpected error in progress check"));
                if (progress.getStatus() != Status.COMPLETED) {
                    progress.setStatus(Status.IN_PROGRESS);
                    progress.setCompletionDate(LocalDateTime.now());
                    userWorkoutProgressRepository.save(progress);
                    log.info("Workout ID {} updated to IN_PROGRESS for user ID {}", workoutPlan.getId(), user.getId());
                }
            }
        }

        return filteredWorkouts.stream()
                .map(WorkoutResponse::new)
                .toList();
    }

    @Override
    public WorkoutResponse getWorkoutById(Long id) {
        log.info("Retrieving workout by ID: {}", id);
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found with ID: " + id));
        log.info("Workout with ID: {} retrieved successfully", id);
        return new WorkoutResponse(workoutPlan);
    }

    @Override
    public void markWorkoutCompleted(Long workoutId) {
        log.info("Marking workout ID {} as completed for user", workoutId);
        User user = loggedInUser.getLoggedInUser();
        if (user == null) {
            throw new IllegalArgumentException("No user is currently logged in");
        }

        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutId)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found with ID: " + workoutId));

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        List<UserWorkoutProgress> existingProgress = userWorkoutProgressRepository.findByUserAndWorkoutPlan(user, workoutPlan);

        UserWorkoutProgress progress;
        if (existingProgress.isEmpty() || existingProgress.stream().noneMatch(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))) {
            progress = new UserWorkoutProgress();
            progress.setUser(user);
            progress.setWorkoutPlan(workoutPlan);
            progress.setStatus(Status.COMPLETED);
            progress.setCompletionDate(LocalDateTime.now());
            userWorkoutProgressRepository.save(progress);
            log.info("Workout ID {} marked as completed for user ID {}", workoutId, user.getId());
        } else {
            progress = existingProgress.stream()
                    .filter(p -> p.getCompletionDate() != null && p.getCompletionDate().isAfter(startOfDay) && p.getCompletionDate().isBefore(endOfDay))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unexpected error in progress check"));
            progress.setStatus(Status.COMPLETED);
            progress.setCompletionDate(LocalDateTime.now());
            userWorkoutProgressRepository.save(progress);
            log.info("Workout ID {} completion status updated for user ID {}", workoutId, user.getId());
        }
    }

    @Override
    public Map<String, List<UserWorkoutProgressResponse>> getAllProgress() {
        log.info("Retrieving all workout progress for logged-in user, grouped by day of the week");
        User user = loggedInUser.getLoggedInUser();
        if (user == null) {
            throw new IllegalArgumentException("No user is currently logged in");
        }

        List<UserWorkoutProgress> progressList = userWorkoutProgressRepository.findByUser(user);
        if (progressList.isEmpty()) {
            log.warn("No progress found for user ID: {}", user.getId());
            return Collections.emptyMap();
        }

        // Group progress entries by day of the week
        Map<String, List<UserWorkoutProgressResponse>> groupedProgress = progressList.stream()
                .filter(progress -> progress.getCompletionDate() != null)
                .collect(Collectors.groupingBy(
                        progress -> progress.getCompletionDate().getDayOfWeek().name(),
                        Collectors.mapping(UserWorkoutProgressResponse::new, Collectors.toList())
                ));

        log.info("Found {} progress entries for user ID {}, grouped into {} days", progressList.size(), user.getId(), groupedProgress.size());
        return groupedProgress;
    }

    private List<String> sanitizeList(List<String> inputList) {
        if (inputList == null) {
            return Collections.emptyList();
        }
        return inputList.stream()
                .map(item -> item.replaceAll("[\"\\[\\]]", "").trim().toLowerCase())
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toList());
    }
}