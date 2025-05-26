package com.example.fitHerWay.quizsurvey.controller;

import com.example.fitHerWay.quizsurvey.dto.BMICalculationRequest;
import com.example.fitHerWay.quizsurvey.dto.BMICalculationResponse;
import com.example.fitHerWay.quizsurvey.dto.UserQuizRequest;
import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import com.example.fitHerWay.quizsurvey.entity.*;
import com.example.fitHerWay.quizsurvey.repository.*;
import com.example.fitHerWay.quizsurvey.service.BMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private BMIService bmiService;
    private final GoalRepository goalRepo;
    private final ExperienceRepository experienceRepo;
    private final AgeGroupRepository ageGroupRepo;
    private final ShapeRepository shapeRepo;
    private final DreamRepository dreamRepo;
    private final ExerciseRepository exerciseRepo;
    private final BestShapeRepository bestShapeRepo;
    private final PreferDayRepository preferDayRepo;
    private final PreferTimeRepository preferTimeRepo;
    private final PreferDayTimeRepository preferDayTimeRepo;
    private final EquipmentRepository equipmentRepo;
    private final UserQuizResponseRepository userResponseRepo;


    public QuizController(GoalRepository goalRepo,
                          ExperienceRepository experienceRepo,
                          AgeGroupRepository ageGroupRepo,
                          ShapeRepository shapeRepo,
                          DreamRepository dreamRepo,
                          ExerciseRepository exerciseRepo,
                          BestShapeRepository bestShapeRepo,
                          PreferDayRepository preferDayRepo,
                          PreferTimeRepository preferTimeRepo,
                          PreferDayTimeRepository preferDayTimeRepo,
                          EquipmentRepository equipmentRepo,
                          UserQuizResponseRepository userResponseRepo) {
        this.goalRepo = goalRepo;
        this.experienceRepo = experienceRepo;
        this.ageGroupRepo = ageGroupRepo;
        this.shapeRepo = shapeRepo;
        this.dreamRepo = dreamRepo;
        this.exerciseRepo = exerciseRepo;
        this.bestShapeRepo = bestShapeRepo;
        this.preferDayRepo = preferDayRepo;
        this.preferTimeRepo = preferTimeRepo;
        this.preferDayTimeRepo = preferDayTimeRepo;
        this.equipmentRepo = equipmentRepo;
        this.userResponseRepo = userResponseRepo;
    }

    // Static data endpoints

    @GetMapping("/goals")
    public List<Goal> getGoals() {
        return goalRepo.findAll();
    }

    @GetMapping("/experience")
    public List<Experience> getExperience() {
        return experienceRepo.findAll();
    }

    @GetMapping("/ages")
    public List<AgeGroup> getAges() {
        return ageGroupRepo.findAll();
    }

    @GetMapping("/shapes")
    public List<Shape> getShapes() {
        return shapeRepo.findAll();
    }

    @GetMapping("/dreams")
    public List<Dream> getDreams() {
        return dreamRepo.findAll();
    }

    @GetMapping("/exercises")
    public List<Exercise> getExercises() {
        return exerciseRepo.findAll();
    }

    @GetMapping("/bestshapes")
    public List<BestShape> getBestShapes() {
        return bestShapeRepo.findAll();
    }

    @GetMapping("/preferdays")
    public List<PreferDay> getPreferDays() {
        return preferDayRepo.findAll();
    }

    @GetMapping("/prefertimes")
    public List<PreferTime> getPreferTimes() {
        return preferTimeRepo.findAll();
    }

    @GetMapping("/preferdaytimes")
    public List<PreferDayTime> getPreferDayTimes() {
        return preferDayTimeRepo.findAll();
    }

    @GetMapping("/equipments")
    public List<Equipment> getEquipments() {
        return equipmentRepo.findAll();
    }

    @PostMapping("/submit")
    public ResponseEntity<UserQuizResponse> submitQuizResponse(@RequestBody UserQuizRequest userResponse) {
        System.out.println("Received quiz submission: " + userResponse);
        UserQuizResponse userQuizResponse = new UserQuizResponse();
        userQuizResponse.setActualAge(userResponse.getActualAge());

        Goal goal = goalRepo.findByLabel(userResponse.getGoal())
                .orElseThrow(() -> new RuntimeException("Goal not found: " + userResponse.getGoal()));
        userQuizResponse.setGoal(goal);

        Experience experience = experienceRepo.findByLabel(userResponse.getExperience())
                .orElseThrow(() -> new RuntimeException("Experience not found: " + userResponse.getExperience()));
        userQuizResponse.setExperience(experience);

        Equipment equipment = equipmentRepo.findByLabel(userResponse.getEquipment())
                .orElseThrow(() -> new RuntimeException("Equipment not found: " + userResponse.getEquipment()));
        userQuizResponse.setEquipment(equipment);

        Dream dream = dreamRepo.findByLabel(userResponse.getDream())
                .orElseThrow(() -> new RuntimeException("Dream not found: " + userResponse.getDream()));
        userQuizResponse.setDream(dream);

        Shape shape = shapeRepo.findByLabel(userResponse.getShape())
                .orElseThrow(() -> new RuntimeException("Shape not found: " + userResponse.getShape()));
        userQuizResponse.setShape(shape);

        BestShape bestShape = bestShapeRepo.findByLabel(userResponse.getBestShape())
                .orElseThrow(() -> new RuntimeException(" Best Shape not found: " + userResponse.getBestShape()));
        userQuizResponse.setBestShape(bestShape);

        PreferDay preferDay = preferDayRepo.findByLabel(userResponse.getPreferDay())
                .orElseThrow(() -> new RuntimeException("Prefer day not found: " + userResponse.getPreferDay()));
        userQuizResponse.setPreferDay(preferDay);

        PreferDayTime preferDayTime = preferDayTimeRepo.findByLabel(userResponse.getPreferDayTime())
                .orElseThrow(() -> new RuntimeException("Prefer day time not found: " + userResponse.getPreferDayTime()));
        userQuizResponse.setPreferDayTime(preferDayTime);

        PreferTime preferTime = preferTimeRepo.findByLabel(userResponse.getPreferTime())
                .orElseThrow(() -> new RuntimeException("Prefer time not found: " + userResponse.getPreferTime()));
        userQuizResponse.setPreferTime(preferTime);


        userQuizResponse.setBmiResult(userResponse.getBmi());

        BMICalculationResponse bmiCalculationResponse = bmiService.getBMIResponse(userResponse.getBmi());
        userQuizResponse.setBmiLevel(bmiCalculationResponse.getLevel());
        userQuizResponse.setBmiMessage(bmiCalculationResponse.getMessage());


        UserQuizResponse saved = userResponseRepo.save(userQuizResponse);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/calculate-bmi")
    public BMICalculationResponse calculateBMI(@RequestBody BMICalculationRequest request) {
        return bmiService.calculateBMI(request);
    }
}
