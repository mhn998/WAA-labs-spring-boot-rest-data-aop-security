package com.example.waa_first_demo.domain;

import com.example.waa_first_demo.domain.dao.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;

    private String name;

    private String email;

    private String password;

    private boolean enabled;

    List<Post> posts = new ArrayList<>();

    Address address;

    List<Role> roles;

    List<UserToken> userTokens;

    public void setUser(User user) {
        this.id = user.id;
        this.name = user.name;
        this.posts = user.posts;
    }

    public User(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.isEnabled();
        this.roles = userEntity.getRoles();
    }


}