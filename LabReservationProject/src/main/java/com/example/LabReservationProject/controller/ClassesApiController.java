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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j //로깅 어노테이션
public class ClassesApiController {

    @Autowired
    ClassesService classesService;

    //전체 정규 수업 && 세미나 조회
    @GetMapping("/api/class/index")
    public ResponseEntity<List<Classes>> indexClasses() {
        List<Classes> arrClass = classesService.indexClass();

        return (arrClass != null) ? ResponseEntity.status(HttpStatus.OK).body(arrClass) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //정규 수업 && 세미나 등록
    @PostMapping("/api/class/create")
    public ResponseEntity<List<Classes>> createClasses(@RequestBody List<ClassesDto> dto) {
        List<Classes> createdClasses = classesService.createClass(dto);

        return (createdClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(createdClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //세미나 수정 -> 요청 Dto에 classNum이 꼭 포함되어야함
    @PatchMapping("/api/class/edit/seminar")
    public ResponseEntity<List<Classes>> updateSeminar(@RequestBody ClassesDto dto) {
        List<Classes> updatedClasses = classesService.updateSeminar(dto);

        return (updatedClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(updatedClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //정규 수업 수정 -> 요청 Dto에 regularClassNum이 꼭 포함되어야함
    @PatchMapping("/api/class/edit/regular")
    public ResponseEntity<List<Classes>> updateRegularClasses(@RequestBody ClassesDto dto) {
        List<Classes> updatedClasses = classesService.updateRegularClass(dto);

        return (updatedClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(updatedClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //정규 수업 삭제 -> 요청 URL에 regularClassNum이 꼭 포함되어야함
    @DeleteMapping("/api/class/delete/regular/{regularClassNum}")
    public ResponseEntity<List<Classes>> deleteRegularClasses(@PathVariable int regularClassNum) {
        List<Classes> deletedClasses = classesService.deleteRegularClass(regularClassNum);

        return (deletedClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(deletedClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //세미나 수정 -> 요청 URL에 classNum이 꼭 포함되어야함
    @DeleteMapping("/api/class/delete/seminar/{classNum}")
    public ResponseEntity<List<Classes>> deleteSeminar(@PathVariable long classNum) {
        List<Classes> deletedClasses = classesService.deleteSeminar(classNum);

        return (deletedClasses != null) ? ResponseEntity.status(HttpStatus.OK).body(deletedClasses) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
