package com.example.etsproject.controller;

import com.example.etsproject.dto.FlightSearchDto;
import com.example.etsproject.entity.Flight;
import com.example.etsproject.service.abstracts.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("flights")
    public ResponseEntity<?> getAll(){
        var result = flightService.getAll();
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("flights/plane/{id}")
    public ResponseEntity<?> getAllByPlaneId(@PathVariable int id){
        var result = flightService.getAllByPlaneId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("flights/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var result = flightService.getById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("flight")
    public ResponseEntity<?> add(@RequestBody Flight flight){
        var result = flightService.add(flight);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping("flights/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Flight flight){
        var result = flightService.update(flight);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("flights/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = flightService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("flights/search")
    public ResponseEntity<?> search(@RequestBody FlightSearchDto flightSearchDto){
        return ResponseEntity.ok(flightService.findByCustomQuery(
                flightSearchDto.getFrom(),
                flightSearchDto.getTo(),
                flightSearchDto.getTime(),
                new Date(),
                flightSearchDto.getBusiness(),
                flightSearchDto.getEconomy()
        ));
    }
}
