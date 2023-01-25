package com.example.waa_first_demo.domain;


import com.example.waa_first_demo.domain.dao.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue
    long addressId;

    String city;
    String zipCode;
    String state;


    @OneToOne
    @JsonBackReference
    UserEntity user;
}
