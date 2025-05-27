package com.example.fitHerWay.auth.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String message;
    private UserResponse user;

    }
