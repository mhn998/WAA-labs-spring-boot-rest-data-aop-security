package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    List<User> findAll();

    Optional<User> findById(long id);

    User save(User user);

    void delete(long id);

    Optional<User> update(long id, User user);

    List<Post> findAllPostsByUserId(long id);

    List<User> findByPosts_SizeGreaterThan(long postsCountGreaterThan);

    List<User> findByPostsTitle(String title);

    Page<User> loadUsers(Pageable pageable);

    List<User> findHavingPostsGreaterThanOneBy(long size, String state);

    List<Post> findAllPostsByUserOnCriteria(long id , String title, long postLength, String device);


    User findByName(String name);

    // needed for security
    User finByEmail(String email);

}
