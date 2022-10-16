package com.example.etsproject.controller;

import com.example.etsproject.dto.PersonDto;
import com.example.etsproject.dto.TicketDto;
import com.example.etsproject.entity.PlaneTicket;
import com.example.etsproject.service.abstracts.PlaneTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class PlaneTicketController {

    private final PlaneTicketService planeTicketService;

    @GetMapping("tickets/{id}")
    public ResponseEntity<?> GetById(@PathVariable int id){
        var result = planeTicketService.findById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("tickets/customer/{id}")
    public ResponseEntity<?> getAllByCustomerId(@PathVariable int id) {
        var result = planeTicketService.findByCustomerId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("tickets/persons/{id}")
    public ResponseEntity<?> getAllByPersonId(@PathVariable int id) {
        var result = planeTicketService.findByPersonId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("tickets/flights/{id}")
    public ResponseEntity<?> getAllByFlightId(@PathVariable int id){
        var result = planeTicketService.findByFlightId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("ticket")
    public ResponseEntity<?> add(@Valid @RequestBody TicketDto ticket){
        var result = planeTicketService.add(ticket);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("tickets/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = planeTicketService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
