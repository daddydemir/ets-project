package com.example.etsproject.repository;

import com.example.etsproject.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Override
    List<Flight> findAll();

    Flight findById(int id);

    List<Flight> findAllByPlaneId(int id);

}
