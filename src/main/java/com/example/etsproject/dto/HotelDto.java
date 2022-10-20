package com.example.etsproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class HotelDto {

    @NotNull
    @NotBlank(message = "Şehir alanı boş bırakılamaz.")
    private String city;

    @NotNull
    @NotBlank(message = "Yetişkin sayısını boş bırakamazsınız.")
    private int adult;

    @NotNull
    @NotBlank(message = "Çocuk sayısını boş bırakamazsınız.")
    private int child;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
