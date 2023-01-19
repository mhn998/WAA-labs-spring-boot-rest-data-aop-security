package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    List<User> findAll();

    Optional<User> findById(long id);

    User save(User p);

    void deleteById(long id);

    Optional<User> update(long id, User p);

}
