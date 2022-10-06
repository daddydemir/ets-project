package com.example.etsproject.repository;

import com.example.etsproject.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    @Override
    List<Plane> findAll();

    Plane findById(int id);

}
