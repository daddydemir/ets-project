package com.example.etsproject.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "authentications")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "date_of_import")
    private Date dateOfImport;

    @ManyToOne(optional = false)
    @JoinColumn(name ="customer_id", referencedColumnName = "id")
    private Customer customer;

}
