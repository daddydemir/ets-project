package com.example.etsproject.repository;

import com.example.etsproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findById(int id);
}
