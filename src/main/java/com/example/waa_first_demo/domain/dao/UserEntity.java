package com.example.waa_first_demo.domain.dao;

import com.example.waa_first_demo.domain.Address;
import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.Role;
import com.example.waa_first_demo.domain.UserToken;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Data
@Entity
@Table(name = "users") //user is reserved word in postgres
@NoArgsConstructor
public class UserEntity { // could be named UserDAO
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    List<Post> posts;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    Address address;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<UserToken> userTokens;


    public UserEntity(String name, String email, String password, boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }
}