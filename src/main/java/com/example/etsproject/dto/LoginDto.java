package com.example.etsproject.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class LoginDto {

    @NotNull
    @NotBlank(message = "Email boş bırakılamaz.")
    private String email;

    @NotNull
    @NotBlank(message = "Parola boş bırakılamaz.")
    private String password;
}
