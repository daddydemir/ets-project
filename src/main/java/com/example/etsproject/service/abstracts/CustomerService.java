package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Customer;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    DataResult<List<Customer>> findAll();

    DataResult<Customer> findById(int id);

    Customer findByEmail(String email);

    Result imageUpdate(int id, MultipartFile file);

    Result add(Customer customer);

    DataResult<Customer> getByEmail(String email);

    Result update(Customer customer);

    Result delete(int id);
}
