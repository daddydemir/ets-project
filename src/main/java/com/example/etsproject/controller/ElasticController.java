package com.example.etsproject.controller;

import com.example.etsproject.entity.ElasticAddress;
import com.example.etsproject.repository.ElasticAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/elastic/")
public class ElasticController {

    private final ElasticAddressRepository repository;

    @PostConstruct
    public void init(){
/*
        repository.save(new ElasticAddress("Antalya"));
        repository.save(new ElasticAddress("Antakya"));
        repository.save(new ElasticAddress("Ankara"));
        repository.save(new ElasticAddress("Anıtkabir"));

        repository.save(new ElasticAddress("Istanbul"));
        repository.save(new ElasticAddress("Izmir"));
        repository.save(new ElasticAddress("Sivas"));
        repository.save(new ElasticAddress("Konya"));
        repository.save(new ElasticAddress("Bursa"));
        repository.save(new ElasticAddress("Manavgat"))

 */
        System.out.println(repository.findAll().toString());

    }

    @GetMapping("{search}")
    public ResponseEntity<?> get(@PathVariable String search){
        return ResponseEntity.ok(this.repository.findByNameLike(search));
    }
}
