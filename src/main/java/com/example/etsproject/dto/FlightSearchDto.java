package com.example.etsproject.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class FlightSearchDto {
    private String from;

    private String to;

    private int business;

    private int economy;

    private Date time;
}
