package com.example.etsproject.controller;

import com.example.etsproject.service.abstracts.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("seats")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(seatService.findAll());
    }
}
