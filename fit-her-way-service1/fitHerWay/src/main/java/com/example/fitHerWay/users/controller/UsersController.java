package com.example.fitHerWay.users.controller;

import com.example.fitHerWay.constant.SystemMessage;
import com.example.fitHerWay.global.BaseController;
import com.example.fitHerWay.global.GlobalApiResponse;
import com.example.fitHerWay.users.dto.request.PasswordRequest;
import com.example.fitHerWay.users.dto.request.UpdateUserRequest;
import com.example.fitHerWay.users.dto.request.UsersRegistrationRequest;
import com.example.fitHerWay.users.dto.request.ValidateOtpRequest;
import com.example.fitHerWay.users.message.UserSwaggerDocumentationMessage;
import com.example.fitHerWay.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController extends BaseController {
    private final UserService usersService;

    @Operation(
            summary = UserSwaggerDocumentationMessage.ADD_USER_SUMMARY,
            description = UserSwaggerDocumentationMessage.ADD_USER_DESCRIPTION
    )
    @PostMapping("/register")
    public ResponseEntity<GlobalApiResponse> registerUser(@ModelAttribute UsersRegistrationRequest usersRegistrationRequest) {
        return successResponse(usersService.registerUser(usersRegistrationRequest), SystemMessage.USER_REGISTERED);
    }

    @Operation(
            summary = UserSwaggerDocumentationMessage.VALIDATE_OTP_SUMMARY,
            description = UserSwaggerDocumentationMessage.VALIDATE_OTP_DESCRIPTION
    )
    @PostMapping("/validate")
    public ResponseEntity<GlobalApiResponse> validateOtp(@RequestBody ValidateOtpRequest validateOtpRequest) {
        return successResponse(usersService.validateOtp(validateOtpRequest), SystemMessage.OTP_VERIFIED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAllUsers() {
        return successResponse(usersService.getAllUsers(), "All users retrieved successfully");
    }

    @GetMapping("/getLoggedInUser")
    public ResponseEntity<GlobalApiResponse> getLoggedInUser() {
        return successResponse(usersService.getLoggedInUser(), "Logged in user retrieved successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GlobalApiResponse> deleteUser(@PathVariable Long id) {
        return successResponse(usersService.deleteUser(id), "User deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateUser(@ModelAttribute UpdateUserRequest userRequest) {
        return successResponse(usersService.updateUser(userRequest), "User updated successfully");
    }

    @PostMapping("/change-password")
    public ResponseEntity<GlobalApiResponse> changePassword(@RequestBody PasswordRequest passwordRequest) {
        return successResponse(usersService.setPassword(passwordRequest), SystemMessage.PASSWORD_SET);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<GlobalApiResponse> getUserInfo() {
        return successResponse(usersService.getUserInfo(), "User information retrieved successfully");
    }


}
