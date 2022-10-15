package com.example.etsproject.repository;

import com.example.etsproject.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findById(int id);

    List<Address> findAllByCity(String city);
}
