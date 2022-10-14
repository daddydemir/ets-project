package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Authentication;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface AuthenticationService {

    DataResult<List<Authentication>> getAuthenticationsByCustomerId(int id);

    Result add(Authentication authentication);

}
