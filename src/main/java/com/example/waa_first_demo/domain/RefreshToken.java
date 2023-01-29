package com.example.waa_first_demo.domain;


import com.example.waa_first_demo.domain.dao.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false)
    private Instant expiryDate;

}
