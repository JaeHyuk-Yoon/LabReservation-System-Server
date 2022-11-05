package com.example.LabReservationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User {
    @Id
    private Long ID;

    @Column
    private String name;
    @Column
    private String Password;
    @Column
    private String PhoneNumber;
    @Column
    private String Email;
    @Column
    private String Job;
    @Column
    private boolean PermissionState;
}
