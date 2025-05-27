package com.example.fitHerWay.auth.entity;

import com.example.fitHerWay.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String accessToken;
    @OneToOne
    private User user;
    private Boolean isExpired;
}
