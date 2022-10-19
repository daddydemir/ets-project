package com.example.etsproject.controller;

import com.example.etsproject.entity.Customer;
import com.example.etsproject.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers")
    public ResponseEntity<?> getAll(){
        var result = customerService.findAll();
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id){
        var result = customerService.findById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    // add customer değil register endpoint i var
    @PutMapping("customers/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Customer customer){
        var result = customerService.update(customer);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = customerService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("customers/image/{id}")
    public ResponseEntity<?> imageUpdate(@PathVariable int id, @RequestParam MultipartFile file){
        var result = customerService.imageUpdate(id, file);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
