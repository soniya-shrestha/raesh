package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.UserQuizResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuizResponseRepository extends JpaRepository<UserQuizResponse,Long> {
}
