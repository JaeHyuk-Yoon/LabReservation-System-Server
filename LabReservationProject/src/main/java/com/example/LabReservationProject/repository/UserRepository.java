package com.example.LabReservationProject.repository;

import com.example.LabReservationProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long>  {
    @Override
    ArrayList<User> findAll();
}
