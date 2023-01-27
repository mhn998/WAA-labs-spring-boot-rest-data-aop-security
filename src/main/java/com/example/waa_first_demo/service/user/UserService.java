package com.example.waa_first_demo.service.user;

import com.example.waa_first_demo.domain.Address;
import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    User save(User p);

    void delete(long id);

    Optional<User> update(long id, User p);

    List<Post> findAllPostsByUserId(long id);

    List<User> findByPosts_SizeGreaterThan(long postsCountGreaterThan);

    List<User> findByPostsTitle(String title);

    Page<User> loadAll(Pageable pageable);

    Address createAddress(long id, Address address);

    List<User> findHavingPostsGreaterThanOneBy(long postsCount, String state);

    // use criteriaQuery to filter posts on specific user which title contains __ and postLength >= ___ and device __
    List<Post> findAllPostsByUserOnCriteria(long id , String title, long postLength, String device);

    User findByName(String userName);

    User findByEmail(String userName);

}
