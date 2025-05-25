package com.example.fitHerWay.auth.controller;

import com.example.fitHerWay.auth.dto.OtpVerificationRequest;
import com.example.fitHerWay.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        otpService.verifyOtp(request);
        return ResponseEntity.ok("OTP verified. Account activated.");
    }
}
