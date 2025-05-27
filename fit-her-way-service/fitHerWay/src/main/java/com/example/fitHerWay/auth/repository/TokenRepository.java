package com.example.fitHerWay.auth.repository;

import com.example.fitHerWay.auth.entity.Token;
import com.example.fitHerWay.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByAccessToken(String accessToken);


    Optional<Token> findByUser(User user);
}
