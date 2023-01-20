package com.example.waa_first_demo.service.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id);

    User save(User p);

    void delete(long id);

    Optional<User> update(long id, User p);

    List<Post> findAllPostsByUserId(long id);

    List<User> findByPosts_SizeGreaterThan(long postsCountGreaterThan);

}
