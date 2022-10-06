package com.example.etsproject.repository;

import com.example.etsproject.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Override
    List<Seat> findAll();
}
