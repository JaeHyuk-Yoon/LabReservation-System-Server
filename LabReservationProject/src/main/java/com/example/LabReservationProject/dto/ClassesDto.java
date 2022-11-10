package com.example.LabReservationProject.dto;

import com.example.LabReservationProject.entity.Classes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClassesDto {
    private Long classNum;

    private String userId;
    private String className;
    private String labNumber;
    private String date;
    private String time;
    private String type;

    public Classes toEntity() {
        return new Classes(
                classNum,
                userId,
                className,
                labNumber,
                date,
                time,
                type
        );
    }
}
