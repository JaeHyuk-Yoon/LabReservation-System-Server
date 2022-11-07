package com.example.LabReservationProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClassesDto {
    private String ID;

    private String ClassName;
    private String LabNumber;
    private java.util.Date Date;
    private String Time;
    private String Type;
}
