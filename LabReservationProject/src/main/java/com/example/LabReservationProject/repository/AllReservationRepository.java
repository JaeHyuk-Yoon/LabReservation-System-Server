package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.AllReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllReservationRepository extends JpaRepository<AllReservation, Long> {
}
