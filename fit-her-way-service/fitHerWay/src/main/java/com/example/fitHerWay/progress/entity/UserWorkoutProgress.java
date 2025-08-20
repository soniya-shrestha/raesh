package com.example.fitHerWay.progress.entity;

import com.example.fitHerWay.admin.entity.WorkoutPlan;
import com.example.fitHerWay.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWorkoutProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlan workoutPlan;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime completionDate;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
