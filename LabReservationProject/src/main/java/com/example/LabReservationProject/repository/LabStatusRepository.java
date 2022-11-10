package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.LabStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabStatusRepository extends JpaRepository<LabStatus, Long> {
}
