package com.example.fitHerWay.users.dto.response;


import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.utils.file.FileUrlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private URI profilePicture;
    private List<RoleResponse> userRole;
    private boolean status;

    public UsersResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhone();
        this.address = user.getAddress();
        this.profilePicture = FileUrlUtil.getFileUri(user.getProfilePic());
        this.status = user.isStatus();
        this.userRole = user.getRole().stream()
                .map(RoleResponse::new)
                .toList();

    }
}
