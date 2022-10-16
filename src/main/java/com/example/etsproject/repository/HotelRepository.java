package com.example.etsproject.repository;

import com.example.etsproject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findById(int id);

    Hotel findByReservationsId(int id);

    @Query("select f from Hotel f where f.emptyRoomSize >= 1")
    List<Hotel> search();
}
