package com.example.etsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    @NotNull
    private int customerId;

    @NotNull
    private int flightId;

    @NotNull
    private PersonDto person;

    @NotNull
    private String seat;


}
