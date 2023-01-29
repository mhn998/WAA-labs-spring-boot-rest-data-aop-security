package com.example.waa_first_demo.domain.dao;

import com.example.waa_first_demo.domain.*;
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
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity { // could be named UserDAO
    @Id
    @GeneratedValue
    private long id;


    @NonNull
    private String name;

    @NonNull
    private String email;


    @NonNull
    private String password;

    @NonNull
    private boolean enabled;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    List<Post> posts;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Address address;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Role> roles;


    public UserEntity(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.posts = user.getPosts();
        this.address = user.getAddress();
        this.roles = user.getRoles();
    }

}