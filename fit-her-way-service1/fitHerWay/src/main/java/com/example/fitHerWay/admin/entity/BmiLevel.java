package com.example.fitHerWay.admin.entity;

public enum BmiLevel {
    UNDERWEIGHT("Underweight"),
    NORMAL("Normal"),
    OVERWEIGHT("Overweight"),
    OBESE("Obese");

    private final String label;

    BmiLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}