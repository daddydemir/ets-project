package com.example.etsproject.controller;

import com.example.etsproject.entity.Address;
import com.example.etsproject.service.abstracts.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var result = addressService.getAddressById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("address/city/{name}")
    public ResponseEntity<?> getByCity(@PathVariable String name){
        var result = addressService.getAllAddressByCityId(name);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("address")
    public ResponseEntity<?> add(@RequestBody Address address){
        var result = addressService.add(address);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
    
    @PutMapping("address/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Address address){
        // TODO: 11/10/2022 Update endpointlerinde id fieldinı kullanmıyorum bunu düzeltmem lazım...
        var result = addressService.update(address);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
