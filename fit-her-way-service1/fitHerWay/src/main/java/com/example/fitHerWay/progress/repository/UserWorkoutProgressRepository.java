package com.example.fitHerWay.progress.repository;



import com.example.fitHerWay.admin.entity.WorkoutPlan;
import com.example.fitHerWay.progress.entity.UserWorkoutProgress;
import com.example.fitHerWay.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserWorkoutProgressRepository extends JpaRepository<UserWorkoutProgress, Long> {
    List<UserWorkoutProgress> findByUserAndWorkoutPlan(User user, WorkoutPlan workoutPlan);
    List<UserWorkoutProgress> findByUserAndCompletionDateBetween(User user, LocalDateTime start, LocalDateTime end);
    List<UserWorkoutProgress> findByUser(User user);
}
