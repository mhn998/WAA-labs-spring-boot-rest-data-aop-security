package com.example.waa_first_demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private boolean enabled;

    List<Post> posts;

    Address address;

    List<Role> roles;

    List<UserToken> userTokens;

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