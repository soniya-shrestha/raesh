package com.example.fitHerWay.users.entity;


import com.example.fitHerWay.global.Auditable;
import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import com.example.fitHerWay.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    @Table(name = "users")
    public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_NAME")
    private String userName;

    @Column(nullable = false, name = "EMAIL_ID", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    private String profilePic;

    @Column(name = "ADDRESS")
    private String address;

    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserQuizResponse> quizResponses;


    private Boolean isActive = true;
    private Boolean isDeleted = false;
    private Boolean isVerified = false;
    }


