package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import com.example.fitHerWay.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQuizResponseRepository extends JpaRepository<UserQuizResponse,Long> {
    Optional<UserQuizResponse> findUserQuizResponseByUser(User user);
}
