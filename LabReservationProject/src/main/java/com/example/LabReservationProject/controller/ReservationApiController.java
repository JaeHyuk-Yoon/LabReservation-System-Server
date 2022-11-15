package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.dto.ReservationDto;
import com.example.LabReservationProject.entity.AllReservation;
import com.example.LabReservationProject.entity.TodayReservation;
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
    public ResponseEntity<List<AllReservation>> createReservation(@PathVariable String id) {
        List<AllReservation> myReservation = reservationService.showMyReservation(id);

        return (!myReservation.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(myReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //오늘 예약내역 전체 조회(조교 기능)
    @GetMapping("/api/reservation/show/today")
    public ResponseEntity<List<TodayReservation>> todayAllReservation() {
        List<TodayReservation> todayReservation = reservationService.showAllTodayReservation();

        return (todayReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(todayReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //모오오오든 정말 모오오오오오ㅗㅇ든 과거까지 모든 예약 내역 조회(조교 기능)
    @GetMapping("/api/reservation/show/all")
    public ResponseEntity<List<AllReservation>> indexReservation() {
        List<AllReservation> allReservation = reservationService.showAllReservation();

        return (!allReservation.isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(allReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //실습실 예약(일과 + 비일과)
    @PostMapping("/api/reservation/create")
    public ResponseEntity<List<TodayReservation>> createReservation(@RequestBody List<ReservationDto> arrDto) {
        List<TodayReservation> createdReservation = reservationService.createReservation(arrDto);

        return (createdReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(createdReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //예약 취소 (내 예약 조회에서 내가 예약한 내역 선택하여 취소)(아직 사용하지 않은 시간에대한 예약만 삭제가능)
    // (조교가 예약 취소할수도 있음)
    @DeleteMapping("/api/reservation/delete/{reservationNum}")
    public ResponseEntity<List<TodayReservation>> createReservation(@PathVariable long reservationNum) {
        List<TodayReservation> deletedReservation = reservationService.deleteReservation(reservationNum);

        return (deletedReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(deletedReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //비일과 예약 승인(조교 기능), 예약의 승인여부 false -> true로 변경
    @GetMapping("/api/reservation/permit/{reservationNum}")
    public ResponseEntity<TodayReservation> permitReservation(@PathVariable long reservationNum) {
        TodayReservation permittedReservation = reservationService.permitReservation(reservationNum);

        return (permittedReservation != null) ? ResponseEntity.status(HttpStatus.OK).body(permittedReservation) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //지금시간 실습실 전체 현황 조회
    @GetMapping("/api/lab/show")
    public ResponseEntity<List<TodayReservation>> showLabStatus() {
        List<TodayReservation> labStatusDto = reservationService.showLabStatus();

        return (labStatusDto != null) ? ResponseEntity.status(HttpStatus.OK).body(labStatusDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
