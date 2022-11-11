package com.example.LabReservationProject.entity;

import lombok.*;

import javax.persistence.*;
@Entity     // DB가 해당 객체를 인식 가능! (해당 클래스로 table을 만든다!)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
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
    @Column
    private int regularClassNum;

    public void classPatch(Classes classes) {

        //classNum은 바뀌면 안됨
//        if(classes.classNum != null)
//            this.classNum = classes.classNum;
//      //등록자도 변경 불가
//        if(classes.userId != null)
//            this.userId = classes.userId;

        if(classes.className != null)
            this.className = classes.className;
        if(classes.labNumber != null)
            this.labNumber = classes.labNumber;
        if(classes.date != null)
            this.date = classes.date;
        if(classes.time != null)
            this.time = classes.time;

        //type도 바꿀수 없음
        //regularClassNum도 바꿀수 없음
    }

}
