package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.ClassesDto;
import com.example.LabReservationProject.entity.Classes;
import com.example.LabReservationProject.repository.ClassesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class ClassesService {
    @Autowired
    ClassesRepository classesRepository;

    //전체 정규 수업 && 세미나 조회
    public List<Classes> indexClass() {

        return classesRepository.findAll();
    }

    //정규 수업 && 세미나 등록 (정규수업일 경우 Client에서 학기 첫날이후로 가장 가까운 해당 수업 요일의 날짜를 Dto의 날짜로 보내주면,
    // 여기서 한시간당 15개의 Dto로 만드는데 그 한시간짜리 수업당 15개 모두 같은 regularClassNum을 가지도록 한다.)
    public List<Classes> createClass(List<ClassesDto> arrDto) {

        List<Classes> arrClasses = new ArrayList<Classes>();
        List<ClassesDto> allRegularClassesDto = new ArrayList<ClassesDto>();

        //정규수업일 경우 1시간 짜리마다 7일간격 15개로 만들기 (2시간 연강이라 2개 dto 들어왔을때는 총 30개 dto가 List에 담김)
        if(arrDto.get(0).getType().equals("Regular")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            String newDate;
            int regularClassNum = createRegularClassNum();
            int count = 0;

            for(ClassesDto dto : arrDto) {
                try {

                    cal.setTime(formatter.parse(dto.getDate()));
                    regularClassNum += count++;

                    dto.setRegularClassNum(regularClassNum);

                    allRegularClassesDto.add(dto);

                    for (int i = 1; i < 15; i++) {
                        cal.add(Calendar.DATE, 7);
                        newDate = formatter.format(cal.getTime());
                        allRegularClassesDto.add(
                                new ClassesDto(
                                        dto.getClassNum(),
                                        dto.getUserId(),
                                        dto.getUserName(),
                                        dto.getClassName(),
                                        dto.getLabNumber(),
                                        newDate,
                                        dto.getTime(),
                                        dto.getType(),
                                        regularClassNum
                                )
                        );
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if(arrDto.get(0).getType().equals("Regular")) {
            for(ClassesDto dto : allRegularClassesDto) {
                arrClasses.add(dto.toEntity());
            }
        } else {
            for (ClassesDto dto : arrDto) {
                arrClasses.add(dto.toEntity());
            }
        }

        if(compareToOriginClass(arrClasses)) { //기존 수업과 중복되는 시간 있는지 비교해주는 함수호출
            //중복 없으면 모두 저장
            return classesRepository.saveAll(arrClasses);
        }
        else {
            //중복 있으면 null
            return null;
        }
    }

    //정규수업 고유번호 생성 함수
    private int createRegularClassNum() {
        List<Classes> allClasses= classesRepository.findAll();
        int num;
        log.info(allClasses.toString());

        if(allClasses.isEmpty()) {
            return 1;
        } else {
            num = allClasses.get(0).getRegularClassNum();
        }

        for(Classes all : allClasses) {
            if(num < all.getRegularClassNum()) {
                num = all.getRegularClassNum();
            }
        }
        return num+1;
    }


    //기존 수업과 중복되는 시간 있는지 비교해서 없으면 true, 이미 있으면 false
    public boolean compareToOriginClass(List<Classes> classes) {
        List<Classes> arrClasses= classesRepository.findAll();

        for(Classes arrClass : arrClasses) {
            for (Classes createClass : classes) {

                if (arrClass.getLabNumber().equals(createClass.getLabNumber()) && //강의실 번호 비교
                        arrClass.getDate().equals(createClass.getDate()) &&     //수업 날짜 비교
                        arrClass.getTime().equals(createClass.getTime()))        //수업 시간 비교
                {
                    //셋다 동일한게 하나라도 있으면 중복된 시간대가 있다는 의미로 false 반환
                    return false;
                }
            }
        }
        //중복 없으면 true 반환
        return true;
    }

    // 정규 수업 수정
    public List<Classes> updateRegularClass(ClassesDto dto) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long gap = 0l;
        Calendar cal = Calendar.getInstance();

        //1. Classes 테이블 전체 Entity 가져온다.
        List<Classes> forCheeckArrClasses = classesRepository.findAll();

        //2. 수정할 Dto와 수정전 Dto 요일차이 구해서 전체중 변경될 Dto에 regularClassNum이 같은 모든 Entity 패치한다.
        try {
            for (Classes all : forCheeckArrClasses) {
                if (all.getRegularClassNum() == dto.getRegularClassNum()) {

                    gap = (formatter.parse(dto.getDate()).getTime() - formatter.parse(all.getDate()).getTime()) /1000/ (24*60*60);
                    break;
                }
            }

            for (Classes all : forCheeckArrClasses) {
                if (all.getRegularClassNum() == dto.getRegularClassNum()) {

                    cal.setTime(formatter.parse(all.getDate()));
                    cal.add(Calendar.DATE, (int)gap);
                    String newDate = formatter.format(cal.getTime());

                    all.setClassName(dto.getClassName());
                    all.setDate(newDate);
                    all.setTime(dto.getTime());
                    all.setLabNumber(dto.getLabNumber());
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

        //3. 패치된 전체 지들끼리 비교해서 중복 확인한다. -> 중복 있으면 널 반환
        for(int i = 0; i < forCheeckArrClasses.size()-1; i++) {
            for(int j = i+1; j < forCheeckArrClasses.size(); j++) {
                if (forCheeckArrClasses.get(i).getLabNumber().equals(forCheeckArrClasses.get(j).getLabNumber()) && //강의실 번호 비교
                        forCheeckArrClasses.get(i).getDate().equals(forCheeckArrClasses.get(j).getDate()) &&     //수업 날짜 비교
                        forCheeckArrClasses.get(i).getTime().equals(forCheeckArrClasses.get(j).getTime()))        //수업 시간 비교
                {
                    //셋다 동일한게 하나라도 있으면 중복된 시간대가 있다는 의미로 false 반환
                    return null;
                }
            }
        }

        //4. 중복 없으면 패치된 Entity 포함하여 업데이트된 모든 수업 saveAll
        return classesRepository.saveAll(forCheeckArrClasses);

    }

    // 세미나 수정
    public List<Classes> updateSeminar(ClassesDto dto) {
//        List<Classes> arrClasses = new ArrayList<Classes>();

        //1. Classes 테이블 전체 Entity 가져온다.
        List<Classes> forCheeckArrClasses = classesRepository.findAll();

        //2. 전체중 같은 classNum끼리(수정될 Entity끼리) 패치한다.
        Classes updateClass = dto.toEntity();

        for(Classes all : forCheeckArrClasses) {
            if (all.getClassNum().equals(updateClass.getClassNum())) {
                all.classPatch(updateClass);
            }
        }
        //3. 패치된 전체 지들끼리 비교해서 중복 확인한다. -> 중복 있으면 널 반환
        for(int i = 0; i < forCheeckArrClasses.size()-1; i++) {
            for(int j = i+1; j < forCheeckArrClasses.size(); j++) {
                if (forCheeckArrClasses.get(i).getLabNumber().equals(forCheeckArrClasses.get(j).getLabNumber()) && //강의실 번호 비교
                        forCheeckArrClasses.get(i).getDate().equals(forCheeckArrClasses.get(j).getDate()) &&     //수업 날짜 비교
                        forCheeckArrClasses.get(i).getTime().equals(forCheeckArrClasses.get(j).getTime()))        //수업 시간 비교
                {
                    //셋다 동일한게 하나라도 있으면 중복된 시간대가 있다는 의미로 false 반환
                    return null;
                }
            }
        }

        //4. 중복 없으면 패치된 Entity 포함하여 업데이트된 모든 수업 saveAll
        return classesRepository.saveAll(forCheeckArrClasses);

    }

    //정규 수업 삭제
    public List<Classes> deleteRegularClass(int regularClassNum) {
        //1. Classes 테이블 전체 Entity 가져온다.
        List<Classes> allClasses = classesRepository.findAll();
        //2. 전체 Entity중에 regularClassNum이 같은건 싹다 지운다.
        for(Classes target : allClasses) {
            if(target.getRegularClassNum() == regularClassNum) {
                classesRepository.delete(target);
            }
        }
        return classesRepository.findAll();
    }

    //세미나 삭제
    public List<Classes> deleteSeminar(long classNum) {
        //1. Classes 테이블 전체 Entity 가져온다.
        List<Classes> allClasses = classesRepository.findAll();
        for(Classes target : allClasses) {
            if(target.getClassNum().equals(classNum)) {
                classesRepository.delete(target);
            }
        }
        return classesRepository.findAll();
    }
}
