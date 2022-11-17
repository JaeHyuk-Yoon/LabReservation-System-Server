package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.ReservationDto;
import com.example.LabReservationProject.entity.AllReservation;
import com.example.LabReservationProject.entity.Classes;
import com.example.LabReservationProject.entity.TodayReservation;
import com.example.LabReservationProject.repository.AllReservationRepository;
import com.example.LabReservationProject.repository.ClassesRepository;
import com.example.LabReservationProject.repository.TodayReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class ReservationService {
    @Autowired
    TodayReservationRepository todayReservationRepository;

    @Autowired
    AllReservationRepository allReservationRepository;

    @Autowired
    ClassesRepository classesRepository;


    //실습실 예약(일과+비일과)
    public List<TodayReservation> createReservation(List<ReservationDto> arrDto) {
        List<TodayReservation> allReservation = todayReservationRepository.findAll();
        List<Classes> classeslist = classesRepository.findAll();
        List<TodayReservation> createReservation = new ArrayList<TodayReservation>();


        for(ReservationDto rdto : arrDto) {
            //정규수업과 세미나랑 중복 체크
            for(Classes classArr : classeslist) {
                if(rdto.getLabNumber().equals(classArr.getLabNumber()) &&
                        rdto.getDate().equals(classArr.getDate()) &&
                        rdto.getTime().substring(0,2).equals(classArr.getTime())) {
                    return null;
                }
            }
            //비일과 예약신청일 경우 오늘 처음 비일과 예약 신청하는것인지 연장신청인지 확인
            if(rdto.isPermissionState() == false) {
                for (TodayReservation reservation : allReservation) {
                    //이미 동일한 아이디로 비일과 예약이 승인된것이 있는지 확인하여 true이면 연장신청 !
                    if (rdto.getId().equals(reservation.getID()) &&
                            reservation.isPermissionState() == true) {
                        //연장신청일 경우 permissionState를 true로 변경
                        if(Integer.parseInt(reservation.getTime().substring(0,2)) < 9 || Integer.parseInt(reservation.getTime().substring(0,2)) >= 17) {
                            rdto.setPermissionState(true);
                        }
                    }
                }
            }
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

    //과거까지 모든 예약내역 전체 조회 (조교기능)
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

    //실습실 비일과 시간 예약 허가
    public TodayReservation permitReservation(long reservationNum) {
        TodayReservation reservation = todayReservationRepository.findById(reservationNum).orElse(null);
        reservation.setPermissionState(true);
        return todayReservationRepository.save(reservation);
    }

    //비일과 예약 일괄 승인 기능 (조교)
    public List<TodayReservation> permitAllReservation() {
        List<TodayReservation> todayReservations = todayReservationRepository.findAll();

        for(TodayReservation today : todayReservations) {
            if(today.isPermissionState() == false) {
                today.setPermissionState(true);
            }
        }
        return todayReservationRepository.saveAll(todayReservations);
    }

    //실습실 현재 현황 조회
    public List<TodayReservation> showLabStatus() {
        List<TodayReservation> reservation = todayReservationRepository.findAll();
        List<TodayReservation> statusReservation = new ArrayList<TodayReservation>();

        Date now = new Date();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String nowMin;

        //30분 이후이면
        if(Integer.parseInt(time.format(now).substring(3)) - 30 >= 0) {
            nowMin = "30";
        }
        else {
            nowMin = "00";
        }
        for(TodayReservation arrReservation : reservation) {

            if(arrReservation.getTime().substring(0,2).equals(time.format(now).substring(0,2)) && arrReservation.getTime().substring(3).equals(nowMin)) {
                statusReservation.add(arrReservation);
            }
        }
            return statusReservation;
    }

    // todayReservation 데이터 모두 -> allReservation 테이블로 이동
    public void moveReservationData() {
        List<TodayReservation> todayReservation = todayReservationRepository.findAll();
        List<AllReservation> newAllReservation = new ArrayList<AllReservation>();

        for(TodayReservation today : todayReservation) {
            newAllReservation.add(TodayReservation.toAll(today));
        }

        allReservationRepository.saveAll(newAllReservation);
        todayReservationRepository.deleteAll();
    }


}
