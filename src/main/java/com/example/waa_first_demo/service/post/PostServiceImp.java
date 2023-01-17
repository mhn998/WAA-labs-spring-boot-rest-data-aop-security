package com.example.waa_first_demo.service.post;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.repo.post.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private PostRepo postRepo;

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepo.findById(id);
    }

    @Override
    public Post save(Post p) {
        return postRepo.save(p);
    }

    @Override
    public Optional<Post> delete(int id) {
        return postRepo.delete(id);
    }

    @Override
    public Optional<Post> update(int id, Post p) {
        return postRepo.update(id, p);
    }

    @Override
    public List<Post> findAllByAuthor(String author) {
        return postRepo.findAllByAuthor(author);
    }


}
