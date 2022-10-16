package com.example.etsproject.controller;

import com.example.etsproject.entity.Room;
import com.example.etsproject.service.abstracts.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("rooms/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var result = roomService.getById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("room")
    public ResponseEntity<?> add(@RequestBody Room room){
        var result = roomService.add(room);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("rooms/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = roomService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
