package com.example.LabReservationProject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BlackList {
    @Id
    private String ID;

    @Column
    private int NumberOfReport;
    @Column
    private int ReportOfToday;
    @Column
    private String RestrictionEndDate;

}
