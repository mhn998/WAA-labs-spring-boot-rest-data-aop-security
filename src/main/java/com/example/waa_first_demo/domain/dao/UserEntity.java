package com.example.waa_first_demo.domain.dao;

import com.example.waa_first_demo.domain.Post;
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
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;

    //    @NonNull
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    List<Post> posts;

}