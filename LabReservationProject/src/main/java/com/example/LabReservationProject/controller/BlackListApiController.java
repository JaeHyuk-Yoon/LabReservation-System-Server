package com.example.LabReservationProject.controller;

import com.example.LabReservationProject.entity.BlackList;
import com.example.LabReservationProject.service.BlackListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j //로깅 어노테이션
public class BlackListApiController {

    @Autowired
    BlackListService blackListService;

    //신고 기능
    @GetMapping("api/blacklist/report/{userId}")
    public ResponseEntity<BlackList> reportUser(@PathVariable String userId) {
        BlackList result = blackListService.reportUser(userId);


        return (result != null) ? ResponseEntity.status(HttpStatus.OK).body(result) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //블랙리스트 조회 기능
    @GetMapping("api/blacklist/report/show")
    public ResponseEntity<List<BlackList>> showBlackList() {
        List<BlackList> result = blackListService.showBlackList();

        return (result != null) ? ResponseEntity.status(HttpStatus.OK).body(result) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
