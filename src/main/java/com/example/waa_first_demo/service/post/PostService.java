package com.example.waa_first_demo.service.post;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAll();

    Optional<Post> findById(long id);

    Post save(Post p);

    void delete(long id);

    Optional<Post> update(long id, Post p);

    List<Post> findAllByAuthor(String author);

}
