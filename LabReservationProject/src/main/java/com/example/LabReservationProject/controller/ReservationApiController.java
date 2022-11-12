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


    //실습실 예약(일과 + 비일과)
    @PostMapping("/api/reservation/create")
    public ResponseEntity<List<TodayReservation>> createReservation(@RequestBody List<ReservationDto> arrDto) {
        List<TodayReservation> createdReservation = reservationService.createReservation(arrDto);

        return (createdReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(createdReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
