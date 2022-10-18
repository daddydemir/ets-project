package com.example.etsproject.service.abstracts;

import com.example.etsproject.dto.CustomerDto;
import com.example.etsproject.dto.JwtResponse;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

public interface AuthService {

    Result registerCustomer(CustomerDto customerDto);

    DataResult<JwtResponse> login(LoginDto loginDto) throws Exception;
    
    Result changePassword(); // TODO: 05/10/2022  another day will be am coding
}
