package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.PreferDayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferDayTimeRepository extends JpaRepository<PreferDayTime,Long> {

    Optional<PreferDayTime> findByLabel(String preferDayTime);
}
