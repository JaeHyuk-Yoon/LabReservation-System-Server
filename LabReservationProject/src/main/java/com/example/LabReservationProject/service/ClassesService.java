package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.ClassesDto;
import com.example.LabReservationProject.entity.Classes;
import com.example.LabReservationProject.repository.ClassesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class ClassesService {
    @Autowired
    ClassesRepository classesRepository;

    //정규 수업 && 세미나 등록
    public List<Classes> createClass(List<ClassesDto> arrDto) {
        List<Classes> arrClasses = new ArrayList<Classes>();

        for(ClassesDto dto : arrDto) {
            arrClasses.add(dto.toEntity());
        }
        log.info(arrClasses.toString());

        if(compareToOriginClass(arrClasses)) { //기존 수업과 중복되는 시간 있는지 비교해주는 함수호출
            log.info(arrClasses.toString());
            //중복 없으면 모두 저장
            return classesRepository.saveAll(arrClasses);
        }
        else {
            //중복 있으면 null
            return null;
        }
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
                    //셋다 동일하면 중복된 수업이 있다는 의미로 false 반환
                    return false;
                }
            }
        }
        //중복 없으면 true 반환
        return true;
    }


}
