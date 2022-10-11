package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "title")
    private String title;

    @Column(name = "floor_no")
    private String floorNo;

    @Column(name = "adult_capacity")
    private int adultCapacity;

    @Column(name = "child_capacity")
    private int childCapacity;

    @Column(name = "price")
    private double price;

    @Column(name = "is_empty")
    private boolean isEmpty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Hotel hotel;

}
