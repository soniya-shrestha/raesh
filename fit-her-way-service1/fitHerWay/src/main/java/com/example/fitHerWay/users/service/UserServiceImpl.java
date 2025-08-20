//package com.example.fitHerWay.users.service;
//
//import com.example.fitHerWay.mail.MailService;
//import com.example.fitHerWay.otp.entity.OTP;
//import com.example.fitHerWay.otp.entity.OTPPurpose;
//import com.example.fitHerWay.otp.service.OTPService;
//import com.example.fitHerWay.role.entity.Role;
//import com.example.fitHerWay.role.repository.RoleRepository;
//import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
//import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
//import com.example.fitHerWay.users.dto.response.UsersResponse;
//import com.example.fitHerWay.users.entity.User;
//import com.example.fitHerWay.users.message.UserExceptionMessage;
//import com.example.fitHerWay.users.message.UserLogMessage;
//import com.example.fitHerWay.users.repository.UserRepository;
//import com.example.fitHerWay.utils.file.FileHandlerUtil;
//import com.example.fitHerWay.utils.logged_in_user.LoggedInUser;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//    private final FileHandlerUtil fileHandlerUtil;
//    private final RoleRepository roleRepository;
//    private final OTPService otpService;
//    private final MailService mailService;
//    private final LoggedInUser loggedInUser;
//
//    @Override
//    public UsersResponse registerUser(UsersRegistrationRequest usersRegistrationRequest) {
//        log.info("Registering user: {}", usersRegistrationRequest);
//        User user = new User();
//        user.setUserName(usersRegistrationRequest.getUserName());
//        user.setEmail(usersRegistrationRequest.getEmail());
//        user.setPhone(usersRegistrationRequest.getPhone());
//        user.setAddress(usersRegistrationRequest.getAddress());
//        user.setProfilePic(fileHandlerUtil.saveFile(usersRegistrationRequest.getProfilePic(), "profileImages").getFileDownloadUri());
//        user.setPassword(new BCryptPasswordEncoder().encode(usersRegistrationRequest.getPassword()));
//        user.setStatus(true);
//
//        // Set role
//        user.setRole(
//                List.of(
//                        roleRepository.findByName(Role.ROLE_USER)
//                                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.ROLE_NOT_FOUND + Role.ROLE_USER))
//                )
//        );
//        userRepository.save(user);
//        log.info("User registered: {}", user);
//
//        OTP otp = otpService.saveOTP(user, OTPPurpose.REGISTER);
//        String userName = user.getUserName();
//        mailService.sendOtpEmail(user.getEmail(), userName, otp.getOtpValue(), otp.getExpiryTime());
//
//        log.info(UserLogMessage.USER_REQUEST_MAIL_SENT , user.getEmail());
//
//
//
//        return new UsersResponse(user);
//    }
//
//    @Override
//    public String validateOtp(ValidateOtpRequest validateOtpRequest) {
//        log.info("Validating OTP for user" );
//        User user = userRepository.findByEmail(validateOtpRequest.getEmail()).orElseThrow(
//                () -> new IllegalArgumentException(UserExceptionMessage.USER_NOT_FOUND + validateOtpRequest.getEmail())
//        );
//        otpService.validateOTP(user, validateOtpRequest.getOtp());
//        user.setIsVerified(true);
//        userRepository.save(user);
//        return "User verified successfully";
//    }
//}

package com.example.fitHerWay.users.service;

import com.example.fitHerWay.mail.MailService;
import com.example.fitHerWay.otp.entity.OTP;
import com.example.fitHerWay.otp.entity.OTPPurpose;
import com.example.fitHerWay.otp.service.OTPService;
import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import com.example.fitHerWay.quizsurvey.repository.UserQuizResponseRepository;
import com.example.fitHerWay.role.entity.Role;
import com.example.fitHerWay.role.repository.RoleRepository;
import com.example.fitHerWay.users.dto.request.PasswordRequest;
import com.example.fitHerWay.users.dto.request.UpdateUserRequest;
import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
import com.example.fitHerWay.users.dto.response.UserDetailResponse;
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

import java.net.URI;
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
    private final UserQuizResponseRepository userQuizResponseRepository;

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


        // Link quiz response if tempToken is provided
            UserQuizResponse quizResponse = userQuizResponseRepository.findById(usersRegistrationRequest.getQuizId())
                    .orElseThrow(() -> new EntityNotFoundException("Quiz response not found for Id: " + usersRegistrationRequest.getQuizId()));
            quizResponse.setUser(user);
            user.setQuizResponses(List.of(quizResponse));
            userQuizResponseRepository.save(quizResponse);




        OTP otp = otpService.saveOTP(user, OTPPurpose.REGISTER);
        String userName = user.getUserName();
        mailService.sendOtpEmail(user.getEmail(), userName, otp.getOtpValue(), otp.getExpiryTime());

        log.info(UserLogMessage.USER_REQUEST_MAIL_SENT, user.getEmail());

        return new UsersResponse(user);
    }

    @Override
    public String validateOtp(ValidateOtpRequest validateOtpRequest) {
        log.info("Validating OTP for user");
        User user = userRepository.findByEmail(validateOtpRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException(UserExceptionMessage.USER_NOT_FOUND + validateOtpRequest.getEmail())
        );
        otpService.validateOTP(user, validateOtpRequest.getOtp());
        user.setIsVerified(true);
        userRepository.save(user);
        return "User verified successfully";
    }

    @Override
    public List<UsersResponse> getAllUsers() {
        log.info("Retrieving all users");
        List<User> users = userRepository.findAllByStatus(true);
        if (users.isEmpty()) {
            log.warn("No users found");
            throw new EntityNotFoundException("User not found");
        }
        return users.stream()
                .map(UsersResponse::new)
                .toList();
    }

    @Override
    public UsersResponse getLoggedInUser() {
        log.info("Retrieving logged in user");
        User user = userRepository.findById(loggedInUser.getLoggedInUser().getId())
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.USER_NOT_FOUND + loggedInUser.getLoggedInUser().getId()));
        return new UsersResponse(user);
    }

    @Override
    public String deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.USER_NOT_FOUND + id));
        user.setStatus(false);
        userRepository.save(user);
        log.info("User with ID: {} deleted successfully", id);
        return "User deleted successfully with ID : " + id;
    }

    @Override
    public UsersResponse updateUser(UpdateUserRequest userRequest) {
        log.info("Updating user with ID: {}", userRequest);
        User user = loggedInUser.getLoggedInUser();
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.USER_NOT_FOUND + user.getId()));
        existingUser.setUserName(userRequest.getUserName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhone(userRequest.getPhone());
        existingUser.setAddress(userRequest.getAddress());
        if (userRequest.getProfilePic() != null) {
            existingUser.setProfilePic(fileHandlerUtil.saveFile(userRequest.getProfilePic(), "profileImages").getFileDownloadUri());
        }
        log.info("User with ID: {} updated successfully", user.getId());
        userRepository.save(existingUser);
        return new UsersResponse(existingUser);
    }

    @Override
    public String setPassword(PasswordRequest passwordRequest) {
        User user = loggedInUser.getLoggedInUser();
        log.info("Setting password for user with ID: {}", user.getId());
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionMessage.USER_NOT_FOUND + user.getId()));
        if (passwordRequest.getNewPassword() == null || passwordRequest.getConfirmNewPassword() == null) {
            log.warn("Password or confirm password is null");
            throw new IllegalArgumentException("Password or confirm password cannot be null.");
        }
        if (passwordRequest.getNewPassword().length() < 6) {
            log.warn("Password is too short");
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        if(passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())) {
            existingUser.setPassword(new BCryptPasswordEncoder().encode(passwordRequest.getNewPassword()));
        }
        else {
            log.warn("Password didn't match");
            throw new IllegalArgumentException("Password doesn't match.");
        }
        userRepository.save(existingUser);
        log.info("Password changed successfully for user with ID: {}", user.getId());
        return "Password changed successfully";
    }

    @Override
    public UserDetailResponse getUserInfo() {
        log.info("Retrieving user information for logged in user");
        User user = loggedInUser.getLoggedInUser();
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        userDetailResponse.setFullName(user.getUserName());
        userDetailResponse.setEmail(user.getEmail());
        userDetailResponse.setPhoneNumber(user.getPhone());
        userDetailResponse.setProfileImageUrl(URI.create(user.getProfilePic()));

        UserQuizResponse quiz = userQuizResponseRepository.findUserQuizResponseByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Quiz response not found for user: " + user.getId()));

        userDetailResponse.setAge(Long.valueOf(quiz.getActualAge())); // Set age
        userDetailResponse.setUserBmi(quiz.getBmiResult()); // Set BMI

        // Set weight with unit
        if ("kg".equalsIgnoreCase(quiz.getBmiWeightUnit()) || "lb".equalsIgnoreCase(quiz.getBmiWeightUnit())) {
            userDetailResponse.setWeight(quiz.getBmiWeight());
        } else {
            userDetailResponse.setWeight(null); // Handle invalid or missing unit
        }

        // Set height based on unit
        if ("cm".equalsIgnoreCase(quiz.getBmiHeightUnit())) {
            if (quiz.getBmiHeight() != null) {
                userDetailResponse.setHeight(quiz.getBmiHeight().longValue());
            } else {
                userDetailResponse.setHeight(null); // Handle null height
            }
        } else if ("ft".equalsIgnoreCase(quiz.getBmiHeightUnit())) {
            userDetailResponse.setHeightFeet(quiz.getBmiHeightFeet() != null ? quiz.getBmiHeightFeet() : 0); // Default to 0 if null
            userDetailResponse.setHeightInch(quiz.getBmiHeightInch() != null ? quiz.getBmiHeightInch() : 0); // Default to 0 if null
        } else {
            userDetailResponse.setHeight(null); // Handle invalid or missing unit
        }

        return userDetailResponse;
    }

}
