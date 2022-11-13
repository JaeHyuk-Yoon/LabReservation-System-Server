package com.example.LabReservationProject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TodayReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 1, 2, 3, .... 자동 생성 어노테이션   // 파라미터=DB가 id를 자동 생성하는
    private long reservationNum;

    @Column
    private String ID;
    @Column
    private String name;
    @Column
    private boolean PermissionState;
    @Column
    private String StartTime;
    @Column
    private String LabNumber;
    @Column
    private String Seat;
    @Column
    private String Date;


    public static AllReservation toAll(TodayReservation today) {
        return new AllReservation(
                today.getReservationNum(),
                today.getID(),
                today.getName(),
                today.isPermissionState(),
                today.getStartTime(),
                today.getLabNumber(),
                today.getSeat(),
                today.getDate()
        );
    }
}
