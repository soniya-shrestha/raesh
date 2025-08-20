package com.example.fitHerWay.users.dto.response;



import com.example.fitHerWay.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private String role;
    private String description;

    public RoleResponse(Role role) {
        this.role = role.getName();
        this.description = role.getDescription();
    }

    public RoleResponse(String name) {
        this.role = name;
    }
}
