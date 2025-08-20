//package com.example.fitHerWay.quizsurvey.dto;
//
//import com.example.fitHerWay.quizsurvey.entity.*;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Data
//public class UserQuizRequest {
//
//    private Integer actualAge;
//
//    private String goal;
//
//    private double bmi;
//
//    private String experience;
//
//    private String equipment;
//
//    private String dream;
//
//    private String preferDay;
//
//    private String preferDayTime;
//
//    private String preferTime;
//
//    private String Shape;
//
//    private String bestShape;
//
//    private String exercise;
//
//
//
//
//}
//

package com.example.fitHerWay.quizsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuizRequest {
    private Integer actualAge;
    private String goal;
    private String experience;
    private Double bmi;
    private String equipment;
    private String dream;
    private String shape;
    private String bestShape;
    private List<String> preferDay; // Updated to List<String>
    private String preferTime;
    private String preferDayTime;
    private List<String> bodyParts; // Added
    // Other fields as needed
}
