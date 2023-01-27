package com.example.waa_first_demo.domain;


import com.example.waa_first_demo.domain.dao.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_tokens")
public class UserToken {

    @Id
    long id;

    String token;

    @ManyToOne
    UserEntity user;
}
