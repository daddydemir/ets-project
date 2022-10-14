package com.example.etsproject.service.concrete;

import com.example.etsproject.core.jwt.JwtUtil;
import com.example.etsproject.service.abstracts.CustomerService;
import com.example.etsproject.service.abstracts.TokenValidationService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class TokenValidationManager implements TokenValidationService {

    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;
    private final CustomerService customerService;


    @Override
    public Result customerIdAndTokenEmailVerification(int customerId) {
        var email = getEmailFromToken();
        if(!email.isSuccess()){
            return new ErrorResult(email.getMessage());
        }
        var user = customerService.findById(customerId);
        if (!user.isSuccess()){
            return new ErrorResult(user.getMessage());
        }
        if(!email.getData().equals(user.getData().getEmail())){
            return new ErrorResult("Customer Id ile mail adresi eşleşmiyor.");
        }
        return new SuccessResult();
    }

    public DataResult<String> getEmailFromToken(){
        final String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null) {
            String token = tokenHeader.substring(7);
            String email = jwtUtil.getEmailFromToken(token);
            return new SuccessDataResult<String>(email,null);
        }
        return new ErrorDataResult<>(null,"Token bulunamadı.");
    }
}
