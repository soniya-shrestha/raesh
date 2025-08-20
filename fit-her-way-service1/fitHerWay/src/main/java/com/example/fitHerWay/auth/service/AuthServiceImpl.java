package com.example.fitHerWay.auth.service;

import com.example.fitHerWay.auth.dto.request.AuthRequest;
import com.example.fitHerWay.auth.dto.response.AuthResponse;
import com.example.fitHerWay.auth.dto.response.UserResponse;
import com.example.fitHerWay.auth.entity.Token;
import com.example.fitHerWay.auth.repository.TokenRepository;
import com.example.fitHerWay.constant.SystemMessage;
import com.example.fitHerWay.security.JwtUtil;
import com.example.fitHerWay.security.UserDetailsServiceImpl;
import com.example.fitHerWay.users.dto.response.RoleResponse;
import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.users.repository.UserRepository;
import com.example.fitHerWay.utils.logged_in_user.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final LoggedInUser loggedInUser;
    @Override
    public AuthResponse login(AuthRequest authRequest) {
        log.info("Login request received: {}", authRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getEmail());

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );

        // Check if the user is verified
        if (!user.getIsVerified()) {
            throw new IllegalArgumentException("User is not verified");
        }

        user.setIsActive(true);
        userRepository.save(user);

        final String accessToken = jwtUtil.generateAccessToken(userDetails);
        final String message = "User logged in successfully";

        if(tokenRepository.findByUser(user).isPresent()) {
            Token token = tokenRepository.findByUser(user).orElseThrow(
                    () -> new EntityNotFoundException(SystemMessage.TOKEN_NOT_FOUND)
            );
            token.setAccessToken(accessToken);
            token.setIsExpired(false);
            tokenRepository.save(token);
        }else {
            Token token = new Token();
            token.setUser(user);
            token.setAccessToken(accessToken);
            token.setIsExpired(false);
            tokenRepository.save(token);
        }


        // Populate UserResponse
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setRole(new RoleResponse(String.valueOf(user.getRole()))); // Single role


        // Return AuthResponse
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setMessage(message);
        authResponse.setUser(userResponse);

        log.info("User logged in: {}", user);
        return authResponse;

    }

    @Override
    public void logout() {
        String accessToken = loggedInUser.getLoggedInUserAccessToken();
        log.info("Logging out user with refresh token: {}", accessToken);
        User user = userRepository.findById(loggedInUser.getLoggedInUser().getId()).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.USER_NOT_FOUND)
        );
        user.setIsActive(false);
        userRepository.save(user);

        Token token = tokenRepository.findByAccessToken(accessToken).orElseThrow(
                () -> new EntityNotFoundException(SystemMessage.TOKEN_NOT_FOUND)
        );
        token.setIsExpired(true);
        tokenRepository.save(token);
        log.info("User logged out: {}", user);
    }
}
