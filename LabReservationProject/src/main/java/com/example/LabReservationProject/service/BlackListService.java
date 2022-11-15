package com.example.LabReservationProject.service;

import com.example.LabReservationProject.entity.BlackList;
import com.example.LabReservationProject.repository.BlackListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class BlackListService {

    @Autowired
    BlackListRepository blackListRepository;
    @Autowired
    UserService userService;

    //신고 기능
    public BlackList reportUser(String id) {
        List<BlackList> blackLists = blackListRepository.findAll();
        for(BlackList black : blackLists) {
            if(black.getID().equals(id)) {
                black.setReportOfToday(black.getReportOfToday()+1);
                black.setNumberOfReport(black.getNumberOfReport()+1);
                if(black.getNumberOfReport() != 0 && black.getNumberOfReport() % 3 == 0) {
                    //4일 뒤 날짜를 setEntdate한다. (3일정지인데 무조건 4일째되는날 아침 9시에 풀리도록하기위해)
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    cal.add(Calendar.DATE, 4);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    black.setRestrictionEndDate(formatter.format(cal.getTime()));
                    userService.nonPermission(id);
                }
                return blackListRepository.save(black);
            }
        }
        return blackListRepository.save(new BlackList(id, 1, 1, null));
    }
}
