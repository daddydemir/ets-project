package com.example.etsproject.dto;

import com.example.etsproject.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {

    private Customer customer;
    private String token;
}
