package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.dto.UserDto;
import com.example.LabReservationProject.entity.User;
import com.example.LabReservationProject.repository.UserRepository;
import com.example.LabReservationProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j //로깅 어노테이션
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //학생 회원가입 및 교수 계정 생성
    @PostMapping("/api/create")
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User user = userService.createUser(dto);

        return (user != null) ? ResponseEntity.status(HttpStatus.OK).body(user):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
