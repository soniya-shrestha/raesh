package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.PreferDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferDayRepository extends JpaRepository<PreferDay,Long> {


    Optional<PreferDay> findByLabel(String preferDay);
}
