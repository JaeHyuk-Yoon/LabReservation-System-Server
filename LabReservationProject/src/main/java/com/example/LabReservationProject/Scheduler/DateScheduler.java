package com.example.LabReservationProject.Scheduler;

import com.example.LabReservationProject.service.BlackListService;
import com.example.LabReservationProject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DateScheduler {

    @Autowired
    ReservationService reservationService;

    @Autowired
    BlackListService blackListService;

    //매일 오전 9시마다 작업을 실행
    //todayReservation 튜플 모두 -> allReservation테이블로 이동
    @Scheduled(cron = "0 0 9 * * ?")
    public void moveReservationData() {
        reservationService.moveReservationData();
    }

    //매일 오전 9시마다 작업을 실행
    //Black List에서 Ban처리된 학생중 정지가 끝나는 날짜인 학생 정지날짜 null로 바꾸고 permissionState true로 업데이트
    @Scheduled(cron = "0 0 9 * * ?")
    public void updateBan() {
        blackListService.updateBan();
    }
}
