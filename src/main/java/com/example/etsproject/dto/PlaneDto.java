package com.example.etsproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlaneDto {

    @NotNull
    @NotBlank(message = "Marka alanı boş bırakılamaz.")
    private String brandName;
}
