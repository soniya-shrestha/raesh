//package com.example.fitHerWay.quizsurvey.entity;
//
//import com.example.fitHerWay.users.entity.User;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@RequiredArgsConstructor
//@Data
//@Entity
//public class UserQuizResponse {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private Integer actualAge;
//
//    @ManyToOne
//    @JoinColumn(name = "age_group_id")
//    private AgeGroup ageGroup;
//
//    @ManyToOne
//    @JoinColumn(name = "goal_id")
//    private Goal goal;
//
//    @ManyToOne
//    @JoinColumn(name = "shape_id")
//    private Shape shape;
//
//    @ManyToOne
//    @JoinColumn(name = "dream_id")
//    private Dream dream;
//
//    @ManyToOne
//    @JoinColumn(name = "experience_id")
//    private Experience experience;
//
//    @ManyToOne
//    @JoinColumn(name = "exercise_id")
//    private Exercise exercise;
//
//    @ManyToOne
//    @JoinColumn(name = "best_shape_id")
//    private BestShape bestShape;
//
//    @ManyToOne
//    @JoinColumn(name = "prefer_day_id")
//    private PreferDay preferDay;
//
//    @ManyToOne
//    @JoinColumn(name = "prefer_time_id")
//    private PreferTime preferTime;
//
//    @ManyToOne
//    @JoinColumn(name = "prefer_day_time_id")
//    private PreferDayTime preferDayTime;
//
//    @ManyToOne
//    @JoinColumn(name = "equipment_id")
//    private Equipment equipment;
//
//    private Double bmiWeight;
//    private String bmiWeightUnit;
//    private Double bmiHeight;
//    private Integer bmiHeightFeet;
//    private Integer bmiHeightInch;
//    private String bmiHeightUnit;
//    private Double bmiResult;
//    private String bmiLevel;
//
//    @Column(columnDefinition = "TEXT")
//    private String bmiMessage;
//
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//
//}
//



package com.example.fitHerWay.quizsurvey.entity;

import com.example.fitHerWay.users.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
public class UserQuizResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    @ElementCollection
    @CollectionTable(name = "user_quiz_prefer_days", joinColumns = @JoinColumn(name = "user_quiz_response_id"))
    @Column(name = "prefer_day")
    private List<String> preferDays; // Changed to List<String> to store multiple days

    @ManyToOne
    @JoinColumn(name = "prefer_time_id")
    private PreferTime preferTime;

    @ManyToOne
    @JoinColumn(name = "prefer_day_time_id")
    private PreferDayTime preferDayTime;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ElementCollection
    @CollectionTable(name = "user_quiz_body_parts", joinColumns = @JoinColumn(name = "user_quiz_response_id"))
    @Column(name = "body_part")
    private List<String> bodyParts; // Added to store preferred body parts

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
