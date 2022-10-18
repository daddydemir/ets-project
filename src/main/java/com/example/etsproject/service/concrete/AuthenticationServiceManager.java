package com.example.etsproject.service.concrete;


import com.example.etsproject.core.business.BusinessRules;
import com.example.etsproject.entity.*;
import com.example.etsproject.repository.*;
import com.example.etsproject.service.abstracts.*;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceManager implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final TokenValidationService tokenValidationService;

    private Result validation(int customerId){
        var result = tokenValidationService.customerIdAndTokenEmailVerification(customerId);
        if (!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Authentication>> getAuthenticationsByCustomerId(int id) {

        var rule = BusinessRules.run(validation(id));
        if(rule != null){
            return new ErrorDataResult<>(null, rule.getMessage());
        }

        var result = authenticationRepository.getAuthenticationsByCustomerId(id);
        if(result.isEmpty()){
            return new ErrorDataResult<>("Hiç kayıt bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Authentication authentication) {
        authenticationRepository.save(authentication);
        return new SuccessResult("Login kaydı eklendi.");
    }
}
