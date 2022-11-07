package com.example.LabReservationProject.dto;

import com.example.LabReservationProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class StudentDto {
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String job;
    private boolean permissionState;

    public User toEntity() { return new User(id, name, password, phoneNumber, email, job, permissionState);}
}
