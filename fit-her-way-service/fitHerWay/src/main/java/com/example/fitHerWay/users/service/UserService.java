package com.example.fitHerWay.users.service;

import com.example.fitHerWay.users.dto.request.PasswordRequest;
import com.example.fitHerWay.users.dto.request.UpdateUserRequest;
import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
import com.example.fitHerWay.users.dto.response.UserDetailResponse;
import com.example.fitHerWay.users.dto.response.UsersResponse;

import java.util.List;

public interface UserService {
    UsersResponse registerUser(UsersRegistrationRequest usersRegistrationRequest);

    String validateOtp(ValidateOtpRequest validateOtpRequest);

    List<UsersResponse> getAllUsers();

    UsersResponse getLoggedInUser();

    String deleteUser(Long id);

    UsersResponse updateUser(UpdateUserRequest userRequest);

    String setPassword(PasswordRequest passwordRequest);

    UserDetailResponse getUserInfo();
}
