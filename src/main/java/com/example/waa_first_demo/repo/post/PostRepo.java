package com.example.waa_first_demo.repo.post;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PostRepo {

    List<Post> findAll();

    Optional<Post> findById(int id);

    Post save(Post p);

    Optional<Post> delete(int id);

    Optional<Post> update(int id, Post p);

    List<Post> findAllByAuthor(String author);
}
