package com.example.fitHerWay.auth.service;


import com.example.fitHerWay.auth.dto.JwtResponse;
import com.example.fitHerWay.auth.dto.LoginRequest;
import com.example.fitHerWay.auth.dto.RegisterRequest;

public interface AuthService {
    JwtResponse register(RegisterRequest request);

    JwtResponse login(LoginRequest request);
}

