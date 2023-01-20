package com.example.waa_first_demo.domain;

import com.example.waa_first_demo.repo.user.UserRepo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @NonNull
//    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @OneToMany
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    List<Post> posts;

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.posts = user.posts;
    }

    public void setUser(User user) {
        this.id = user.id;
        this.name = user.name;
        this.posts = user.posts;
    }

}