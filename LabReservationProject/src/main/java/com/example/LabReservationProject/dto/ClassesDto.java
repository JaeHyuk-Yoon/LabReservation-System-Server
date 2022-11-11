package com.example.LabReservationProject.dto;

import com.example.LabReservationProject.entity.Classes;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ClassesDto {
    private Long classNum;

    private String userId;
    private String className;
    private String labNumber;
    private String date;
    private String time;
    private String type;
    private int regularClassNum;

    public Classes toEntity() {
        return new Classes(
                classNum,
                userId,
                className,
                labNumber,
                date,
                time,
                type,
                regularClassNum
        );
    }
}
