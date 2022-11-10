package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
