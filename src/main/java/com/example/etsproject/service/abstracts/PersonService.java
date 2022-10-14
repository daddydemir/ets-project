package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Person;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface PersonService {

    DataResult<List<Person>> findAll();

    DataResult<Person> findById(int id);

    Result add(Person person);

    Result update(Person person);

    Result delete(int id);
}
