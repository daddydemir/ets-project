package com.example.etsproject.service.abstracts;

import com.example.etsproject.utils.Result;

public interface TokenValidationService {

    Result customerIdAndTokenEmailVerification(int customerId);
}
