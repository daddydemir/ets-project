package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "planes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "flights"})
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "brand_image")
    private String brandImage;

    @OneToMany(mappedBy = "plane", targetEntity = Flight.class, fetch = FetchType.LAZY)
    private List<Flight> flights;
}
