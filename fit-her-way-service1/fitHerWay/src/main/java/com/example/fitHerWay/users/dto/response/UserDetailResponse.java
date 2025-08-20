package com.example.fitHerWay.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private URI profileImageUrl;
    private Long age;
    private Double weight;
    private Long height; // For height in cm
    private Integer heightFeet; // For height in feet
    private Integer heightInch; // For height in inches
    private Double userBmi;
}
