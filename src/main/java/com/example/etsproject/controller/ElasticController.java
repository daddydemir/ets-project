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
        ElasticAddress ea = new ElasticAddress();
        ea.setName("Antalya");

        ElasticAddress ea1 = new ElasticAddress();
        ea1.setName("Antakya");

        ElasticAddress ea2 = new ElasticAddress();
        ea2.setName("Ankara");

        ElasticAddress ea3 = new ElasticAddress();
        ea3.setName("Anıtkabir");

        repository.save(ea);
        repository.save(ea1);
        repository.save(ea2);
        repository.save(ea3);

        repository.save(new ElasticAddress("Istanbul"));
        repository.save(new ElasticAddress("Izmir"));
        repository.save(new ElasticAddress("Sivas"));
        repository.save(new ElasticAddress("Konya"));
        repository.save(new ElasticAddress("Bursa"));
        repository.save(new ElasticAddress("Manavgat"));


    }

    @GetMapping("{search}")
    public ResponseEntity<?> get(@PathVariable String search){
        return ResponseEntity.ok(this.repository.findByNameLike(search));
    }
}
