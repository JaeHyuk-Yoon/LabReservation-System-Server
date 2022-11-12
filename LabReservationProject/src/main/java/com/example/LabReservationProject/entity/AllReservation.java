package com.example.LabReservationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class AllReservation {
    @Id
    private long reservationNum;

    @Column
    private String ID;
    @Column
    private String name;
    @Column
    private boolean PermissionState;
    @Column
    private String Time;
    @Column
    private String LabNumber;
    @Column
    private String Seat;
    @Column
    private String Date;
}
