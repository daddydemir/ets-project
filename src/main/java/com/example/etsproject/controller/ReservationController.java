package com.example.etsproject.controller;

import com.example.etsproject.entity.Reservation;
import com.example.etsproject.service.abstracts.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("reservations/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var result = reservationService.getById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("reservations/customer/{id}")
    public ResponseEntity<?> getCustomerId(@PathVariable int id){
        var result = reservationService.getByCustomerId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("reservations/hotel/{id}")
    public ResponseEntity<?> getHotelId(@PathVariable int id){
        var result = reservationService.getByHotelId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("reservation")
    public ResponseEntity<?> add(@RequestBody Reservation reservation){
        var result = reservationService.add(reservation);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("reservations/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = reservationService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
