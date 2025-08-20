package com.example.fitHerWay.admin.service.impl;

import com.example.fitHerWay.admin.dto.NutritionPlanRequest;
import com.example.fitHerWay.admin.dto.NutritionPlanResponse;
import com.example.fitHerWay.admin.entity.BmiLevel;
import com.example.fitHerWay.admin.entity.NutritionPlan;
import com.example.fitHerWay.admin.repository.NutritionPlanRepository;
import com.example.fitHerWay.admin.service.NutritionPlanService;

import com.example.fitHerWay.utils.file.FileHandlerUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class NutritionPlanServiceImpl implements NutritionPlanService {

    private final NutritionPlanRepository repo;
    private final FileHandlerUtil fileHandlerUtil;

    @Override
    public NutritionPlanResponse create(NutritionPlanRequest request) {
        NutritionPlan plan = new NutritionPlan();
        setFields(request, plan);
        plan.setCreatedAt(LocalDateTime.now());
        repo.save(plan);
        return new NutritionPlanResponse(plan); // Fixed
    }

    @Override
    public NutritionPlanResponse update(Long id, NutritionPlanRequest request) {
        NutritionPlan plan = repo.findById(id).orElseThrow();
        setFields(request, plan);
        repo.save(plan);
        return new NutritionPlanResponse(plan); // Fixed
    }

    private void setFields(NutritionPlanRequest request, NutritionPlan plan) {
        plan.setTitle(request.getTitle());
        plan.setDescription(request.getDescription());
        plan.setType(request.getType());
        plan.setCaloriesPerDay(request.getCaloriesPerDay());
        plan.setBmiLevel(request.getBmiLevel());
        plan.setGoalType(request.getGoalType());
        plan.setStatus(request.isStatus());
        plan.setCreatedBy(request.getCreatedBy());

        MultipartFile imageFile = request.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imagePath = fileHandlerUtil.saveFile(imageFile, "nutritionImages").getFileDownloadUri();
                plan.setImageUrl(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<NutritionPlanResponse> getAll() {
        return repo.findAll().stream().map(NutritionPlanResponse::new
        ).collect(Collectors.toList());
    }

    @Override
    public NutritionPlanResponse getById(Long id) {
        log.info("Fetching Nutrition Plan with ID: {}", id);
        NutritionPlan plan = repo.findById(id).orElseThrow(() -> new RuntimeException("Nutrition Plan not found"));
        log.info("Nutrition Plan found: {}", plan);
        return new NutritionPlanResponse(plan);
    }

    @Override
    public List<NutritionPlanResponse> getUserPlans(BmiLevel bmiLevel, String goalType) {
        // Log to debug
        log.info("Fetching plans for bmiLevel={}, goalType={}", bmiLevel, goalType);

        return repo.findByStatusTrueAndBmiLevelAndGoalTypeIgnoreCase(bmiLevel, goalType)
                .stream()
                .map(NutritionPlanResponse::new)
                .collect(Collectors.toList());
    }



}

