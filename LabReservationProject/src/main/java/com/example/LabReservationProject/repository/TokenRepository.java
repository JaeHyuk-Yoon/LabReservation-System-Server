package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<User, Long> {
}