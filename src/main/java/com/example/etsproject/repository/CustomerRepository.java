package com.example.etsproject.repository;

import com.example.etsproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    @Override
//    List<Customer> findAll();

    Customer findCustomerById(int id);

    Customer findCustomerByEmail(String email);


}
