package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.ReservationDto;
import com.example.LabReservationProject.entity.TodayReservation;
import com.example.LabReservationProject.repository.ClassesRepository;
import com.example.LabReservationProject.repository.TodayReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class ReservationService {
    @Autowired
    TodayReservationRepository reservationRepository;


    //실습실 예약(일과+비일과)
    public List<TodayReservation> createReservation(List<ReservationDto> arrDto) {

        List<TodayReservation> createReservation = new ArrayList<TodayReservation>();
        for(ReservationDto rdto : arrDto) {
            createReservation.add(rdto.toTodayEntity());
        }
        reservationRepository.saveAll(createReservation);
        return reservationRepository.findAll();
    }
}
