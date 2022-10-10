package com.example.etsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name ="hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "room_size")
    private int roomSize;

    @Column(name = "empty_room_size")
    private int emptyRoomSize;

    @Column(name = "star_size")
    private int starSize;

    @Column(name = "information")
    private String information;

    @Column(name = "image")
    private String image;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Address address;

    @OneToMany(mappedBy = "hotel", targetEntity = Room.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room> room;

    @OneToMany(mappedBy = "hotel", targetEntity = Reservation.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
}
