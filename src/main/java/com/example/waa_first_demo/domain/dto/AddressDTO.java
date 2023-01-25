package com.example.waa_first_demo.domain.dto;

import com.example.waa_first_demo.domain.dao.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data

public class AddressDTO {
    @Id
    @GeneratedValue
    long addressId;

    String city;
    String zipCode;
    String state;

}
