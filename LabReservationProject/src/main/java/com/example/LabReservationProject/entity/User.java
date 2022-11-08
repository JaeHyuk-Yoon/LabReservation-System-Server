package com.example.LabReservationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User {
    @Id
    private String ID;

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

    public void userPatch(User user) {
        //ID는 바뀌면 안됨
//        if(user.ID != null)
//            this.ID = user.ID;

        if(user.name != null)
            this.name = user.name;
        if(user.Password != null)
            this.Password = user.Password;
        if(user.PhoneNumber != null)
            this.PhoneNumber = user.PhoneNumber;
        if(user.Email != null)
            this.Email = user.Email;
        if(user.Job != null)
            this.Job = user.Job;

        //permissionState도 바꿀수 없음
    }
}
