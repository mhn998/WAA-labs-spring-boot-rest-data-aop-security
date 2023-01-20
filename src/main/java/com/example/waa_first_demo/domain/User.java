package com.example.waa_first_demo.domain;

import com.example.waa_first_demo.repo.user.UserRepo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.posts = user.posts;
    }

    public User(int i, String name) {
        this.id = i;
        this.name = name;
    }

    public void setUser(User user) {
        this.id = user.id;
        this.name = user.name;
        this.posts = user.posts;
    }

}