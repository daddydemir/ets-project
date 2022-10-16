package com.example.etsproject.controller;

import com.example.etsproject.core.jwt.JwtService;
import com.example.etsproject.dto.CustomerDto;
import com.example.etsproject.dto.LoginDto;
import com.example.etsproject.service.abstracts.AuthService;
import com.example.etsproject.utils.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        var result = authService.login(loginDto);
        if (!result.isSuccess()) {
            return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new SuccessDataResult<Object>(jwtService.createJwtToken(loginDto)));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CustomerDto customerDto) throws Exception {
        var result = authService.registerCustomer(customerDto);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
