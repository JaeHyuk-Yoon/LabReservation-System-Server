package com.example.LabReservationProject.dto;

import com.example.LabReservationProject.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String job;
    private boolean permissionState;

    public User toEntity() {
        return new User(
                id,
                name,
                password,
                phoneNumber,
                email,
                job,
                permissionState
        );
    }

    public static UserDto createUserDto(User user) {
        return new UserDto(
                user.getID(),
                user.getName(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getJob(),
                user.isPermissionState()
        );
    }


}
