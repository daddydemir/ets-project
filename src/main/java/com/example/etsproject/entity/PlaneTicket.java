package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "plane_tickets")
public class PlaneTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "seat_id")
    private int seatId;

    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "customer_id")
    private int customerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Person person;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Seat seat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Flight flight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;
}
