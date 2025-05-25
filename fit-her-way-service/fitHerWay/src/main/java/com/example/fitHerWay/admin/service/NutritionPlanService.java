package com.example.fitHerWay.admin.service;

import com.example.fitHerWay.admin.dto.NutritionPlanDTO;
import com.example.fitHerWay.admin.entity.NutritionPlan;
import com.example.fitHerWay.admin.repository.NutritionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionPlanService {
    @Autowired
    private NutritionPlanRepository repo;

    private NutritionPlanDTO toDTO(NutritionPlan plan) {
        return new NutritionPlanDTO(plan.getId(), plan.getTitle(), plan.getDescription(), plan.getType());
    }

    private NutritionPlan toEntity(NutritionPlanDTO dto) {
        NutritionPlan plan = new NutritionPlan();
        plan.setTitle(dto.getTitle());
        plan.setDescription(dto.getDescription());
        plan.setType(dto.getType());
        return plan;
    }

    public List<NutritionPlanDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public NutritionPlanDTO add(NutritionPlanDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    public NutritionPlanDTO update(Long id, NutritionPlanDTO dto) {
        NutritionPlan plan = repo.findById(id).orElseThrow();
        plan.setTitle(dto.getTitle());
        plan.setDescription(dto.getDescription());
        plan.setType(dto.getType());
        return toDTO(repo.save(plan));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

