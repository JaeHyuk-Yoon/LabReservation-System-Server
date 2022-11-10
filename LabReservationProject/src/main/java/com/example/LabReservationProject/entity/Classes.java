package com.example.LabReservationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 1, 2, 3, .... 자동 생성 어노테이션   // 파라미터=DB가 id를 자동 생성하는
    private Long classNum;

    @Column
    private String userId;
    @Column
    private String className;
    @Column
    private String labNumber;
    @Column
    private String date;
    @Column
    private String time;
    @Column
    private String type;

}
