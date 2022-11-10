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
public class ReservationDto {
    private String id;

    private String name;
    private String permissionState;
    private String time;
    private String labNumber;
    private String seat;
    private java.util.Date date;
}
