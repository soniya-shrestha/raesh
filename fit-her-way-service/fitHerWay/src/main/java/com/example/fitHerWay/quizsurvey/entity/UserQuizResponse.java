package com.example.fitHerWay.quizsurvey.entity;

import com.example.fitHerWay.quizsurvey.entity.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserQuizResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer actualAge;

    @ManyToOne
    @JoinColumn(name = "age_group_id")
    private AgeGroup ageGroup;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private Shape shape;

    @ManyToOne
    @JoinColumn(name = "dream_id")
    private Dream dream;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "best_shape_id")
    private BestShape bestShape;

    @ManyToOne
    @JoinColumn(name = "prefer_day_id")
    private PreferDay preferDay;

    @ManyToOne
    @JoinColumn(name = "prefer_time_id")
    private PreferTime preferTime;

    @ManyToOne
    @JoinColumn(name = "prefer_day_time_id")
    private PreferDayTime preferDayTime;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private Double bmiWeight;
    private String bmiWeightUnit;
    private Double bmiHeight;
    private Integer bmiHeightFeet;
    private Integer bmiHeightInch;
    private String bmiHeightUnit;
    private Double bmiResult;
    private String bmiLevel;

    @Column(columnDefinition = "TEXT")
    private String bmiMessage;

    private LocalDateTime createdAt = LocalDateTime.now();


}

