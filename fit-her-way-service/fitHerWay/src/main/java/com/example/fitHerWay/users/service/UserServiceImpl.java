package com.example.fitHerWay.users.service;

import com.example.fitHerWay.mail.MailService;
import com.example.fitHerWay.otp.entity.OTP;
import com.example.fitHerWay.otp.entity.OTPPurpose;
import com.example.fitHerWay.otp.service.OTPService;
import com.example.fitHerWay.role.entity.Role;
import com.example.fitHerWay.role.repository.RoleRepository;
import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
import com.example.fitHerWay.users.dto.response.UsersResponse;
import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.users.message.UserExceptionMessage;
import com.example.fitHerWay.users.message.UserLogMessage;
import com.example.fitHerWay.users.repository.UserRepository;
import com.example.fitHerWay.utils.file.FileHandlerUtil;
import com.example.fitHerWay.utils.logged_in_user.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FileHandlerUtil fileHandlerUtil;
    private final RoleRepository roleRepository;
    private final OTPService otpService;
    private final MailService mailService;
    private final LoggedInUser loggedInUser;

    @Override
    public UsersResponse registerUser(UsersRegistrationRequest usersRegistrationRequest) {
        log.info("Registering user: {}", usersRegistrationRequest);
        User user = new User();
        user.setUserName(usersRegistrationRequest.getUserName());
        user.setEmail(usersRegistrationRequest.getEmail());
        user.setPhone(usersRegistrationRequest.getPhone());
        user.setAddress(usersRegistrationRequest.getAddress());
        user.setProfilePic(fileHandlerUtil.saveFile(usersRegistrationRequest.getProfilePic(), "profileImages").getFileDownloadUri());
        user.setPassword(new BCryptPasswordEncoder().encode(usersRegistrationRequest.getPassword()));
        user.setStatus(true);

        // Set role
        user.setRole(
                List.of(
                        roleRepository.findByName(Role.ROLE_USER)
                                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.ROLE_NOT_FOUND + Role.ROLE_USER))
                )
        );
        userRepository.save(user);
        log.info("User registered: {}", user);

        OTP otp = otpService.saveOTP(user, OTPPurpose.REGISTER);
        String userName = user.getUserName();
        mailService.sendOtpEmail(user.getEmail(), userName, otp.getOtpValue(), otp.getExpiryTime());

        log.info(UserLogMessage.USER_REQUEST_MAIL_SENT , user.getEmail());



        return new UsersResponse(user);
    }

    @Override
    public String validateOtp(ValidateOtpRequest validateOtpRequest) {
        log.info("Validating OTP for user" );
        User user = userRepository.findByEmail(validateOtpRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException(UserExceptionMessage.USER_NOT_FOUND + validateOtpRequest.getEmail())
        );
        otpService.validateOTP(user, validateOtpRequest.getOtp());
        user.setIsVerified(true);
        userRepository.save(user);
        return "User verified successfully";
    }
}
