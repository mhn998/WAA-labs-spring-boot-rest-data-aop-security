package com.example.waa_first_demo.repo.post.Imp;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.repo.post.PostRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("in-memory")
public class InMemoryPostRepoImp implements PostRepo {

    private static ArrayList<Post> posts;
    static {
        posts = new ArrayList<>();
        Post p1 = new Post(111,"Test","This is test 1","Muhannad");
        Post p2 = new Post(112,"Test 2","This is test 2","Mugh");
        Post p3 = new Post(112,"Test 3","This is test 3","Ameer");
        Post p4 = new Post(112,"Test 4","This is test 4","Mohammad");
        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
        posts.add(p4);
    }

    @Override
    public List<Post> findAll() {
        return Collections.unmodifiableList(posts);
    }

    @Override
    public Optional<Post> findById(long id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
    }

    @Override
    public Post save(Post p) {
        posts.add(p);
        return p;
    }

    @Override
    public void deleteById(long id) {
        Optional<Post> toBeDeleted = posts.stream().filter(post -> post.getId() == id).findFirst();
        toBeDeleted.ifPresent(post -> posts.remove(post));
    }

    @Override
    public Optional<Post> update(long id, Post p) {
        Optional<Post> toBeUpdated = posts.stream().filter(post -> post.getId() == id).findFirst();
        toBeUpdated.ifPresent(post -> {
            post.setPost(p);
        });

        return toBeUpdated;
    }

    @Override
    public List<Post> findAllByAuthor(String author) {
        return posts.stream().filter(post -> post.getAuthor().equals(author)).collect(Collectors.toList());
    }




}
