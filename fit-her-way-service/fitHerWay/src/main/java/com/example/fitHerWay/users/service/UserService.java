package com.example.fitHerWay.users.service;

import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
import com.example.fitHerWay.users.dto.response.UsersResponse;

public interface UserService {
    UsersResponse registerUser(UsersRegistrationRequest usersRegistrationRequest);

    String validateOtp(ValidateOtpRequest validateOtpRequest);
}
