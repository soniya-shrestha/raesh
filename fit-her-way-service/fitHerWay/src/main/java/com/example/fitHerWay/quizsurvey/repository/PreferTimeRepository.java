package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.PreferTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferTimeRepository extends JpaRepository<PreferTime,Long> {

    Optional<PreferTime> findByLabel(String preferTime);
}
