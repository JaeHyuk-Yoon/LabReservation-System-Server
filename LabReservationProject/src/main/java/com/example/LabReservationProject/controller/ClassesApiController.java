package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.dto.ClassesDto;
import com.example.LabReservationProject.entity.Classes;
import com.example.LabReservationProject.repository.ClassesRepository;
import com.example.LabReservationProject.service.ClassesService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j //로깅 어노테이션
public class ClassesApiController {

    @Autowired
    ClassesService classesService;

    //정규 수업 && 세미나 등록
    @PostMapping("/api/class/create")
    public ResponseEntity<List<Classes>> createClasses(@RequestBody List<ClassesDto> dto) {
        List<Classes> createdClasses = classesService.createClass(dto);

        return (createdClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(createdClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}