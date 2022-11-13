package com.example.LabReservationProject.dto;

import com.example.LabReservationProject.entity.TodayReservation;
import com.example.LabReservationProject.entity.User;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ReservationDto {

    private long reservationNum;
    private String id;
    private String name;
    private boolean permissionState;
    private String time;
    private String labNumber;
    private String seat;
    private String date;


    public TodayReservation toTodayEntity() { return new TodayReservation(reservationNum, id, name, permissionState, time, labNumber, seat, date);}
}
