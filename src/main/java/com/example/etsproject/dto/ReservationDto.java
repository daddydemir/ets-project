package com.example.etsproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReservationDto {

    @NotNull
    @NotBlank(message = "customer id geçersiz.")
    private int customerId;

    @NotNull
    @NotBlank(message = "geçersiz otel id.")
    private int hotelId;

    @NotNull
    @NotBlank(message = "required.")
    private Date startDate;

    @NotNull
    @NotBlank(message = "required.")
    private Date endDate;
}
