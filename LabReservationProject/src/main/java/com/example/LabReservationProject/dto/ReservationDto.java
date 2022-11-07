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
    private String ID;

    private String name;
    private String PermissionState;
    private String Time;
    private String LabNumber;
    private String Seat;
    private java.util.Date Date;
}
