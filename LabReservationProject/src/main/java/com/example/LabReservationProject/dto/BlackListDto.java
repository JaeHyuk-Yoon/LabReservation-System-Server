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
public class BlackListDto {
    private String ID;

    private int NumberOfReport;
    private int ReportOfToday;
    private Date RestrictionEndDate;
}
