package com.example.etsproject.service.abstracts;

import com.example.etsproject.dto.CustomerDto;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.utils.Result;

public interface AuthService {

    Result registerCustomer(CustomerDto customerDto);

    Result login(LoginDto loginDto);
    
    Result changePassword(); // TODO: 05/10/2022  another day will be am coding
}
