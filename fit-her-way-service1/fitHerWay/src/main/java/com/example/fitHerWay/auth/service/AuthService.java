package com.example.fitHerWay.auth.service;


import com.example.fitHerWay.auth.dto.request.AuthRequest;
import com.example.fitHerWay.auth.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);

    void logout();
}
