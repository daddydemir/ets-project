package com.example.etsproject.repository;

import com.example.etsproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findById(int id);

    @Query("select f from Room f where f.adultCapacity >= :_adult  and f.childCapacity >= :_child and f.isEmpty = true")
    List<Room> search(
            @Param("_adult") int adult,
            @Param("_child") int child
    );
}
