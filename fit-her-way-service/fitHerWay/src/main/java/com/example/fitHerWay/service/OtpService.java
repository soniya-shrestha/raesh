package com.example.fitHerWay.service;

import com.example.fitHerWay.auth.dto.OtpVerificationRequest;
import com.example.fitHerWay.auth.entity.User;
import com.example.fitHerWay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final UserRepository userRepository;

    public void verifyOtp(OtpVerificationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        user.setEnabled(true);
        user.setOtp(null);
        userRepository.save(user);
    }
}
