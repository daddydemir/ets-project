package com.example.etsproject.controller;


import com.example.etsproject.service.abstracts.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("authentications/{id}")
    public ResponseEntity<?> getAll(@PathVariable int id){
        var result = authenticationService.getAuthenticationsByCustomerId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

}
