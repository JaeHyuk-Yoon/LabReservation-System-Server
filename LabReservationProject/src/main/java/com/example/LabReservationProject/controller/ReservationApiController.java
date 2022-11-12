package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.dto.ReservationDto;
import com.example.LabReservationProject.entity.TodayReservation;
import com.example.LabReservationProject.entity.User;
import com.example.LabReservationProject.service.ClassesService;
import com.example.LabReservationProject.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j //로깅 어노테이션
public class ReservationApiController {
    @Autowired
    ReservationService reservationService;

    //내 예약 조회
    @GetMapping("/api/reservation/show/{id}")
    public ResponseEntity<List<TodayReservation>> createReservation(@PathVariable String id) {
        List<TodayReservation> myReservation = reservationService.showMyReservation(id);

        return (!myReservation.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(myReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //전체 예약내역 조회
    @GetMapping("/api/reservation/show/today")
    public ResponseEntity<List<TodayReservation>> indexReservation() {
        List<TodayReservation> todayReservation = reservationService.showAllTodayReservation();

        return (!todayReservation.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(todayReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //실습실 예약(일과 + 비일과)
    @PostMapping("/api/reservation/create")
    public ResponseEntity<List<TodayReservation>> createReservation(@RequestBody List<ReservationDto> arrDto) {
        List<TodayReservation> createdReservation = reservationService.createReservation(arrDto);

        return (createdReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(createdReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //예약 취소 (내 예약 조회에서 내가 예약한 내역 선택하여 취소)
    @DeleteMapping("/api/reservation/delete/{reservationNum}")
    public ResponseEntity<List<TodayReservation>> createReservation(@PathVariable long reservationNum) {
        List<TodayReservation> deletedReservation = reservationService.deleteReservation(reservationNum);

        return (deletedReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(deletedReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
