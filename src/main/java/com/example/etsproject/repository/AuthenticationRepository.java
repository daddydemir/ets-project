package com.example.etsproject.repository;

import com.example.etsproject.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {

    List<Authentication> getAuthenticationsByCustomerId(int id);
}
