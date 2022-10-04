package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "authentications", "tickets"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile_image")
    private String profileImage;

    @OneToMany(mappedBy = "customer", targetEntity = Authentication.class, fetch = FetchType.LAZY)
    private List<Authentication> authentications;

    @OneToMany(mappedBy = "customer", targetEntity = PlaneTicket.class, fetch = FetchType.LAZY)
    private List<PlaneTicket> tickets;
}
