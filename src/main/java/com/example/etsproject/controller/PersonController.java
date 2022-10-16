package com.example.etsproject.controller;

import com.example.etsproject.entity.Person;
import com.example.etsproject.service.abstracts.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class PersonController {

    private final PersonService personService;

    @GetMapping("persons")
    public ResponseEntity<?> getAll(){
        var result = personService.findAll();
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id){
        var result = personService.findById(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("person")
    public ResponseEntity<?> add(@RequestBody Person person){
        var result = personService.add(person);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Person person){
        var result = personService.update(person);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        var result = personService.delete(id);
        if (!result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
