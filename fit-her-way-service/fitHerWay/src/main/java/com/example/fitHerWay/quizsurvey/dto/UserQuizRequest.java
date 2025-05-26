package com.example.fitHerWay.quizsurvey.dto;

import com.example.fitHerWay.quizsurvey.entity.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserQuizRequest {

    private Integer actualAge;

    private String goal;

    private double bmi;

    private String experience;

    private String equipment;

    private String dream;

    private String preferDay;

    private String preferDayTime;

    private String preferTime;

    private String Shape;

    private String bestShape;

    private String exercise;




}

