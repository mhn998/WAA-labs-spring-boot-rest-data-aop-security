package com.example.waa_first_demo.repo.post;

import com.example.waa_first_demo.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepo {

    List<Post> findAll();

    Optional<Post> findById(long id);

    Post save(Post p);

    void deleteById(long id);

    Optional<Post> update(long id, Post p);

    List<Post> findAllByAuthor(String author);
}
