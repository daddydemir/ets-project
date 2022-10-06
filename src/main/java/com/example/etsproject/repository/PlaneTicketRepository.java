package com.example.etsproject.repository;

import com.example.etsproject.entity.PlaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneTicketRepository extends JpaRepository<PlaneTicket, Integer> {


    PlaneTicket findById(int id);

    List<PlaneTicket> findAllByCustomerId(int id);

    List<PlaneTicket> findAllByPersonId(int id);

    List<PlaneTicket> findAllByFlightId(int id);
}
