package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.dto.UserDto;
import com.example.LabReservationProject.entity.Token;
import com.example.LabReservationProject.entity.User;
import com.example.LabReservationProject.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j //로깅 어노테이션
public class TokenApiController {
    @Autowired
    TokenService tokenService;

    //토큰 조회
    @GetMapping("/api/token/show")
    public ResponseEntity<Token> showToken() {
        Token token = tokenService.showToken();

        return (token != null) ? ResponseEntity.status(HttpStatus.OK).body(token) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //토큰 생성
    @GetMapping("/api/token/create")
    public ResponseEntity<Token> createToken() {
        Token token = tokenService.createToken();

        return (token != null) ? ResponseEntity.status(HttpStatus.OK).body(token) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
