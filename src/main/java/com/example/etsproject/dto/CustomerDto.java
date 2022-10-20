package com.example.etsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull
    @NotBlank(message = "İsim alanı boş bırakılamaz.")
    private String name;

    @NotNull
    @NotBlank(message = "Soyisim alanı boş bırakılamaz.")
    private String surname;

    @NotNull
    @NotBlank(message = "Parola alanı boş bırakılamaz.")
    private String password;

    @NotNull
    @NotBlank(message = "Mail alanı boş bırakılamaz.")
    private String email;

    @NotNull
    @NotBlank(message = "Telefon numarası alanı boş bırakılamaz.")
    private String phone;
}
