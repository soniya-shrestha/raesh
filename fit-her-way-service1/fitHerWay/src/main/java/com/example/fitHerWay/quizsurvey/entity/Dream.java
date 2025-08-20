package com.example.fitHerWay.quizsurvey.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;



}
