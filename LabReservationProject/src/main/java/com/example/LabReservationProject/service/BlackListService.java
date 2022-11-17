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
            //이미 한번이상 신고된적 있으면
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
        //최초 신고 당했을때
        return blackListRepository.save(new BlackList(id, 1, 1, null));
    }

    //Black List에서 Ban처리된 학생중 정지가 끝나는 날짜인 학생 정지날짜 null로 바꾸고 permissionState true로 업데이트
    public void updateBan() {
        List<BlackList> blackLists = blackListRepository.findAll();
        //오늘날짜 받아서 오늘이랑 같은 날짜 있으면 null로
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for(BlackList black : blackLists) {
            black.setReportOfToday(0);
            if(black.getRestrictionEndDate().equals(formatter.format(now))) {
                black.setRestrictionEndDate(null);
                userService.permission(black.getID());
            }
        }
    }

    //조회 기능
    public List<BlackList> showBlackList() {
        return blackListRepository.findAll();
    }
}
