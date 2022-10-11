package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile_image")
    private String profileImage;

    @OneToMany(mappedBy = "customer", targetEntity = Authentication.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Authentication> authentications;

    @OneToMany(mappedBy = "customer", targetEntity = PlaneTicket.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlaneTicket> tickets;

    @OneToMany(mappedBy = "customer", targetEntity = Reservation.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
}
