package com.example.fitHerWay.auth.dto.response;


import com.example.fitHerWay.users.dto.response.RoleResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponse {
    private String userName;
    private RoleResponse role;
}
