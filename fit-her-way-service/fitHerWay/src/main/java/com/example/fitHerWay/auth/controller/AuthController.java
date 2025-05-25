package com.example.fitHerWay.auth.controller;

import com.example.fitHerWay.auth.dto.JwtResponse;
import com.example.fitHerWay.auth.dto.RegisterRequest;
import com.example.fitHerWay.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        JwtResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}
