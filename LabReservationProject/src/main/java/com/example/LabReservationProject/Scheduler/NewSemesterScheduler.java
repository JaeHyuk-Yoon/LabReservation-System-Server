package com.example.LabReservationProject.Scheduler;

import com.example.LabReservationProject.service.TokenService;
import com.example.LabReservationProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//새학기 시작할때 마다 실행될 작업(3월 1일, 9월 1일)
@Component
public class NewSemesterScheduler {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    //3월 1일 00시 00분, 9월 1일 00시 00분 마다 작업을 실행
    //모든 학생들 permissionState false로 변경
    @Scheduled(cron = "0 0 0 1 3,9 ?")
    public void initStudentPermissionState() {
        userService.initStudentPermissionState();
    }

    //3월 1일 00시 00분, 9월 1일 00시 00분 마다 작업을 실행
    //id 1번 Token value를 null로 변경
    @Scheduled(cron = "0 0 0 1 3,9 ?")
    public void tokenToNUll() {
        tokenService.tokenToNULL();
    }

}
