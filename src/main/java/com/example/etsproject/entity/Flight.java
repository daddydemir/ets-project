package com.example.etsproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "plane_id")
    private int planeId;

    @Column(name = "expedition_no")
    private String expeditionNo;

    @Column(name = "departure_point")
    private String departurePoint;

    @Column(name = "destination")
    private String destination;

    @Column(name = "empty_business")
    private int emptyBusiness;

    @Column(name = "empty_economy")
    private int emptyEconomy;

    @Column(name = "price_business")
    private double priceBusiness;

    @Column(name = "price_economy")
    private double priceEconomy;

    @Column(name = "departure_time")
    private Date departureTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plane_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Plane plane;

    @OneToMany(mappedBy = "flight", targetEntity = PlaneTicket.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlaneTicket> tickets;
}
