package com.example.etsproject.repository;

import com.example.etsproject.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findById(int id);

    List<Reservation> findByCustomerId(int customerId);

    List<Reservation> findByHotelId(int hotelId);
}
