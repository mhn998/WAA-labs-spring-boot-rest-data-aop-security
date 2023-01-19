package com.example.waa_first_demo.service.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.repo.user.RDBMSUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private RDBMSUserRepo userRepo;
//    private RDBMSPostRepo postRepo;

    public List<User> findAll() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    public User save(User p) {
        return userRepo.save(p);
    }

    public void delete(long id) {
         userRepo.deleteById(id);
    }

    public Optional<User> update(long id, User u) {
        Optional<User> byId = userRepo.findById(id);
        byId.ifPresent((user) -> {
            user.setUser(u);
        });

        return byId;
    }

    public List<Post> findAllPostsByUserId(long id) {
        Optional<User> byId = userRepo.findById(id);
        AtomicReference<List<Post>> posts = new AtomicReference<>();
        byId.ifPresent((user) -> {
            posts.set(user.getPosts());
        });

        return posts.get();
    }

    public List<User> findByPosts_SizeGreaterThan(long postsCountGreaterThan) {
        return userRepo.findByPosts_SizeGreaterThan(postsCountGreaterThan);
    }

}
