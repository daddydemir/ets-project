package com.example.etsproject.repository;

import com.example.etsproject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    List<Person> findAll();

    Person findPersonById(int id);
}
