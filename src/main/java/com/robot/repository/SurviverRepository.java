package com.robot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robot.model.Surviver;

@Repository
public interface SurviverRepository extends JpaRepository<Surviver, Long> {
}
