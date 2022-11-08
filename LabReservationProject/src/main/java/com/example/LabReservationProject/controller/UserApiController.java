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
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j //로깅 어노테이션
public class UserApiController {

    @Autowired
    private UserService userService;

    //학생 회원가입 및 교수 계정 생성
    @PostMapping("/api/create")
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User createdUser = userService.createUser(dto);

        return (createdUser != null) ? ResponseEntity.status(HttpStatus.OK).body(createdUser) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //로그인
    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody UserDto dto) {
        User resultUser = userService.login(dto);

        return (resultUser != null) ? ResponseEntity.status(HttpStatus.OK).body(resultUser) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 토큰 인증시 -> permissionState 1로 변경해야함 *(클라이언트에서 id만 실어서 보내주면됨)
    @PatchMapping("/api/permission/{id}")
    public ResponseEntity<User> permission(@PathVariable String id) {
        User permissionResultUser = userService.permission(id);

        return (permissionResultUser != null) ? ResponseEntity.status(HttpStatus.OK).body(permissionResultUser) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
