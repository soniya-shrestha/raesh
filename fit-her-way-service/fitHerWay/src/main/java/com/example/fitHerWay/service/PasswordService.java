//package com.example.fitHerWay.service;
//
//import com.example.fitHerWay.auth.dto.ChangePasswordRequest;
//import com.example.fitHerWay.auth.dto.ForgotPasswordRequest;
//import com.example.fitHerWay.auth.entity.User;
//import com.example.fitHerWay.auth.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@Service
//@RequiredArgsConstructor
//public class PasswordService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public void forgotPassword(ForgotPasswordRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//        user.setOtp(otp);
//        userRepository.save(user);
//
//        // Simulate email
//        System.out.println("Password reset OTP: " + otp);
//    }
//
//    public void changePassword(ChangePasswordRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!user.getOtp().equals(request.getOtp())) {
//            throw new RuntimeException("Invalid OTP");
//        }
//
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        user.setOtp(null);
//        userRepository.save(user);
//    }
//}
