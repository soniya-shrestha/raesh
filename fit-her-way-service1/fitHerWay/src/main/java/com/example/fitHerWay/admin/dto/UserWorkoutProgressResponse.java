package com.example.fitHerWay.admin.dto;


import com.example.fitHerWay.progress.entity.UserWorkoutProgress;
import com.example.fitHerWay.progress.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWorkoutProgressResponse {
    private Long id;
    private Long userId;
    private Long workoutPlanId;
    private String workoutName;
    private Status status;
    private LocalDateTime completionDate;
    private LocalDateTime createdAt;

    public UserWorkoutProgressResponse(UserWorkoutProgress progress) {
        this.id = progress.getId();
        this.userId = progress.getUser().getId();
        this.workoutPlanId = progress.getWorkoutPlan().getId();
        this.workoutName = progress.getWorkoutPlan().getWorkoutName();
        this.status = progress.getStatus();
        this.completionDate = progress.getCompletionDate();
        this.createdAt = progress.getCreatedAt();
    }
}