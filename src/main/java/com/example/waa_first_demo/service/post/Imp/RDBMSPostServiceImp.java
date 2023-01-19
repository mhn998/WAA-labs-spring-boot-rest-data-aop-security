package com.example.waa_first_demo.service.post.Imp;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.repo.post.Imp.RDBMSPostRepo;
import com.example.waa_first_demo.service.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Profile("rdbms")
public class RDBMSPostServiceImp implements PostService {

    private RDBMSPostRepo rdbmsPostRepo;

    public List<Post> findAll() {
        return StreamSupport.stream(rdbmsPostRepo.findAll().spliterator(), false)
                .toList();
    }


    public Optional<Post> findById(long id) {
        return rdbmsPostRepo.findById(id);
    }

    public Post save(Post post) {
        return rdbmsPostRepo.save(post);
    }

    public Optional<Post> update(long id, Post post) {
        Optional<Post> byId = rdbmsPostRepo.findById(id);
        byId.ifPresent((p) -> {
            p.setPost(post);
        });
        return byId;
    }


    //.orElseThrow(() -> new RuntimeException("Cannot Find Item By ID to delete:" + id));
    public void delete(long id) {
        Optional<Post> post = rdbmsPostRepo.findById(id);
        post.ifPresent((p) -> {
            rdbmsPostRepo.deleteById(id);
        });
    }

    public List<Post> findAllByAuthor(String author) {
        return null;
    }
}
