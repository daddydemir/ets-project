package com.example.etsproject.repository;

import com.example.etsproject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findById(int id);

    Hotel findByReservationsId(int id);
}
