package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.ReservationDto;
import com.example.LabReservationProject.entity.AllReservation;
import com.example.LabReservationProject.entity.TodayReservation;
import com.example.LabReservationProject.repository.AllReservationRepository;
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
    TodayReservationRepository todayReservationRepository;

    @Autowired
    AllReservationRepository allReservationRepository;


    //실습실 예약(일과+비일과)
    public List<TodayReservation> createReservation(List<ReservationDto> arrDto) {

        List<TodayReservation> createReservation = new ArrayList<TodayReservation>();
        for(ReservationDto rdto : arrDto) {
            createReservation.add(rdto.toTodayEntity());
        }
        todayReservationRepository.saveAll(createReservation);
        return todayReservationRepository.findAll();
    }

    //예약 삭제
    public List<TodayReservation> deleteReservation(long reservationNum) {
        TodayReservation target = todayReservationRepository.findById(reservationNum).orElse(null);

        if(target==null) {
            return null;
        }

        todayReservationRepository.delete(target);
        return todayReservationRepository.findAll();
    }

    //내 예약 조회
    public List<AllReservation> showMyReservation(String id) {
        List<TodayReservation> allTodayReservation = todayReservationRepository.findAll();
        List<AllReservation> allReservation = allReservationRepository.findAll();

        List<AllReservation> myReservation = new ArrayList<AllReservation>();

        for(TodayReservation reservation : allTodayReservation) {
            if(reservation.getID().equals(id)) {
                myReservation.add(TodayReservation.toAll(reservation));
            }
        }

        for(AllReservation reservation : allReservation) {
            if(reservation.getID().equals(id)) {
                myReservation.add(reservation);
            }
        }
        return myReservation;
    }

    //오늘 예약내역 전체 조회 (조교기능)
    public List<TodayReservation> showAllTodayReservation() {
        return todayReservationRepository.findAll();
    }

    public List<AllReservation> showAllReservation() {
        List<TodayReservation> allTodayReservation = todayReservationRepository.findAll();
        List<AllReservation> allReservation = allReservationRepository.findAll();

        List<AllReservation> myReservation = new ArrayList<AllReservation>();

        for(TodayReservation reservation : allTodayReservation) {
            myReservation.add(TodayReservation.toAll(reservation));
        }

        for(AllReservation reservation : allReservation) {
            myReservation.add(reservation);
        }
        return myReservation;
    }
}
