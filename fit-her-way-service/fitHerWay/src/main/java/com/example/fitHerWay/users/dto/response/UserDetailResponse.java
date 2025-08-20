package com.example.fitHerWay.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private URI profileImageUrl;
    private Long age;
    private Long weight;
    private Long height;
    private Long userBmi;

    public UserDetailResponse(UserDetailResponse userDetailResponse) {
        this.fullName = userDetailResponse.getFullName();
        this.email = userDetailResponse.getEmail();
        this.phoneNumber = userDetailResponse.getPhoneNumber();
        this.profileImageUrl = userDetailResponse.getProfileImageUrl();
        this.age = userDetailResponse.getAge();
        this.weight = userDetailResponse.getWeight();
        this.height = userDetailResponse.getHeight();
        this.userBmi = userDetailResponse.getUserBmi();
    }
}
