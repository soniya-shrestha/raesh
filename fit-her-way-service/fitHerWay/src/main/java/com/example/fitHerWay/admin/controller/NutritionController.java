package com.example.fitHerWay.admin.controller;

import com.example.fitHerWay.admin.dto.NutritionPlanRequest;
import com.example.fitHerWay.admin.dto.NutritionPlanResponse;
import com.example.fitHerWay.admin.entity.BmiLevel;
import com.example.fitHerWay.admin.service.NutritionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/nutrition")
public class NutritionController {
private final NutritionPlanService service;

public NutritionController(NutritionPlanService service) {
    this.service = service;
}

@PostMapping("/create")
public NutritionPlanResponse create(@ModelAttribute NutritionPlanRequest request) {
    return service.create(request);
}

@PutMapping("/{id}")
public NutritionPlanResponse update(@PathVariable Long id, @ModelAttribute NutritionPlanRequest request) {
    return service.update(id, request);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    service.delete(id);
}

@GetMapping("/getAll")
public List<NutritionPlanResponse> getAll() {
    return service.getAll();
}

@GetMapping("/getById/{id}")
public NutritionPlanResponse getOne(@PathVariable Long id) {
    return service.getById(id);
}

@GetMapping("/user")
public List<NutritionPlanResponse> getForUser(@RequestParam BmiLevel bmiLevel,
                                              @RequestParam String goalType) {
    return service.getUserPlans(bmiLevel, goalType);
}
}
