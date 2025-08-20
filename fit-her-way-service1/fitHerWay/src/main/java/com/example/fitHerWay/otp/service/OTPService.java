package com.example.fitHerWay.otp.service;


import com.example.fitHerWay.otp.entity.OTP;
import com.example.fitHerWay.otp.entity.OTPPurpose;
import com.example.fitHerWay.users.entity.User;

public interface OTPService {
    OTP saveOTP(User user, OTPPurpose purpose);

    void validateOTP(User user, String otp);

    OTP getOTP(String otp, OTPPurpose purpose);
}
