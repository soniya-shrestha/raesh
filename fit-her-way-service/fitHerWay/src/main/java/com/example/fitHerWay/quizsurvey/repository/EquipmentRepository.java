package com.example.fitHerWay.quizsurvey.repository;

import com.example.fitHerWay.quizsurvey.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    Optional<Equipment> findByLabel(String equipment);
}
