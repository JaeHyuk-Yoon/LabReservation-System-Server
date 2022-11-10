package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.TodayReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayReservationRepository extends JpaRepository<TodayReservation, Long> {
}
