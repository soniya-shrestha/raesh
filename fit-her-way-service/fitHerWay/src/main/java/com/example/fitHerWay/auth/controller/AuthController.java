
package com.example.fitHerWay.auth.controller;


import com.example.fitHerWay.auth.dto.request.AuthRequest;
import com.example.fitHerWay.auth.message.AuthSwaggerDocumentationMessage;
import com.example.fitHerWay.auth.service.AuthService;
import com.example.fitHerWay.constant.SystemMessage;
import com.example.fitHerWay.global.BaseController;
import com.example.fitHerWay.global.GlobalApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;


    @Operation(
            summary = AuthSwaggerDocumentationMessage.CREATE_LOGIN_SUMMARY,
            description = AuthSwaggerDocumentationMessage.CREATE_LOGIN_DESCRIPTION
    )
    @PostMapping("/login")
    public ResponseEntity<GlobalApiResponse> login(@RequestBody AuthRequest authRequest) {
        return successResponse(authService.login(authRequest), SystemMessage.USER_LOGGED_IN_MSG);
    }

    @Operation(
            summary = AuthSwaggerDocumentationMessage.CREATE_LOGOUT_SUMMARY,
            description = AuthSwaggerDocumentationMessage.CREATE_LOGOUT_DESCRIPTION
    )
    @GetMapping("/logout")
    public ResponseEntity<GlobalApiResponse> logout() {
        authService.logout();
        return successResponse(SystemMessage.USER_LOGGED_OUT_MSG);
    }

}
