package com.example.fitHerWay.utils.logged_in_user;



import com.example.fitHerWay.auth.repository.TokenRepository;
import com.example.fitHerWay.role.entity.Role;
import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggedInUser {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("No user found with email: " + email)
        );
    }

    public List<Role> getLoggedInUserRole() {
        User user = getLoggedInUser();
        return user.getRole();
    }


    public String getLoggedInUserAccessToken(){
        User user = getLoggedInUser();
        return tokenRepository.findByUser(user).orElseThrow(
                () -> new EntityNotFoundException("No refresh token found for user: " + user.getEmail())
        ).getAccessToken();
    }
}
