//package com.example.fitHerWay.auth.controller;
//
//import com.example.fitHerWay.auth.dto.ChangePasswordRequest;
//import com.example.fitHerWay.auth.dto.ForgotPasswordRequest;
//import com.example.fitHerWay.service.PasswordService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/password")
//@RequiredArgsConstructor
//public class PasswordController {
//
//    private final PasswordService passwordService;
//
//    @PostMapping("/forgot")
//    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
//        passwordService.forgotPassword(request);
//        return ResponseEntity.ok("OTP sent to your email.");
//    }
//
//    @PostMapping("/change")
//    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
//        passwordService.changePassword(request);
//        return ResponseEntity.ok("Password changed successfully.");
//    }
//}
