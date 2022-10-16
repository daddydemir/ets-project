package com.example.etsproject.controller;

import com.example.etsproject.dto.HotelDto;
import com.example.etsproject.entity.Hotel;
import com.example.etsproject.service.abstracts.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("hotels/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var result = hotelService.getById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("hotels/reservations/{id}")
    public ResponseEntity<?> getByReservationId(@PathVariable int id){
        var result = hotelService.getByReservationId(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("hotel")
    public ResponseEntity<?> add(@RequestBody Hotel hotel){
        var result = hotelService.add(hotel);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("hotels/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = hotelService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("hotels/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Hotel hotel){
        // TODO: 11/10/2022 id kullanılacak...
        var result = hotelService.update(hotel);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/hotels/search")
    public ResponseEntity<?> search(@RequestBody HotelDto hotelDto){
        var result = hotelService.search(hotelDto);
        if(!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
