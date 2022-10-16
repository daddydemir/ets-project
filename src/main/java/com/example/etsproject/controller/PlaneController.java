package com.example.etsproject.controller;

import com.example.etsproject.entity.Plane;
import com.example.etsproject.service.abstracts.PlaneService;
import com.example.etsproject.utils.ErrorDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("planes")
    public ResponseEntity<?> getAll(){
        var result = planeService.getAllPlanes();
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("planes/{id}")
    public ResponseEntity<?> getPlaneById(@PathVariable int id){
        var result = planeService.getPlaneById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("plane")
    public ResponseEntity<?> add(@RequestBody Plane plane){
        var result = planeService.add(plane);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("planes/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Plane plane){
        var result = planeService.update(plane);
        // TODO: 06/10/2022 id yi neden kullanmadım bu işte bi yanlışlık var...
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("planes/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = planeService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
