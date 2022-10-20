package com.example.etsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @NotNull
    @NotBlank(message = "İsim boş bırakılamaz")
    private String name;

    @NotNull
    @NotBlank(message = "Soyisim boş bırakılamaz")
    private String surname;

    @NotNull
    @NotBlank(message = "Email boş bırakılamaz")
    private String email;

    @NotNull
    @NotBlank(message = "Kimlik boş bırakılamaz")
    private String identityNo;

    @NotNull
    @NotBlank(message = "Cinsiyet boş bırakılamaz.")
    private String gender;
}
