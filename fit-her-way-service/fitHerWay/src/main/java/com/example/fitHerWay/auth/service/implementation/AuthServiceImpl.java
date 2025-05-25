package com.example.fitHerWay.auth.service.implementation;

import com.example.fitHerWay.auth.dto.JwtResponse;
import com.example.fitHerWay.auth.dto.LoginRequest;
import com.example.fitHerWay.auth.dto.RegisterRequest;
import com.example.fitHerWay.auth.entity.User;
import com.example.fitHerWay.auth.entity.UserType;
import com.example.fitHerWay.auth.repository.UserRepository;
import com.example.fitHerWay.auth.service.AuthService;
import com.example.fitHerWay.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserType.USER)
                .enabled(false) // email verification or OTP step
                .build();

         userRepository.save(user);

        // Generate token
        String jwtToken = jwtService.generateToken(user.getEmail());

        return JwtResponse.builder()
                .token(jwtToken)
                .message("Registered successfully. Please verify your OTP.")
                .build();
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified");
        }

        String jwtToken = jwtService.generateToken(user.getEmail());

        return JwtResponse.builder()
                .token(jwtToken)
                .message("Login successful")
                .build();
    }
}
