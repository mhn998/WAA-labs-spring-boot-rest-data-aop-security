package com.example.waa_first_demo.service.post.Imp;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.repo.post.PostRepo;
import com.example.waa_first_demo.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Profile("in-memory")
public class InMemoryPostServiceImp implements PostService {

    private PostRepo InMemoryPostRepo;

    @Override
    public List<Post> findAll() {
        return InMemoryPostRepo.findAll();
    }

    @Override
    public Optional<Post> findById(long id) {
        return InMemoryPostRepo.findById(id);
    }

    @Override
    public Post save(Post p) {
        return InMemoryPostRepo.save(p);
    }

    @Override
    public void delete(long id) {
        InMemoryPostRepo.deleteById(id);
    }

    @Override
    public Optional<Post> update(long id, Post p) {
        return InMemoryPostRepo.update(id, p);
    }

    @Override
    public List<Post> findAllByAuthor(String author) {
        return InMemoryPostRepo.findAllByAuthor(author);
    }


}
