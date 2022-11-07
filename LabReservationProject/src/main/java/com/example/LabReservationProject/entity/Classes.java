package com.example.LabReservationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Classes {
    @Id
    private String ID;

    @Column
    private String ClassName;
    @Column
    private String LabNumber;
    @Column
    private Date Date;
    @Column
    private String Time;
    @Column
    private String Type;
}
