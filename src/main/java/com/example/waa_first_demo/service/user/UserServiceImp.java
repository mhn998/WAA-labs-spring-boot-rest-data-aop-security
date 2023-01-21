package com.example.waa_first_demo.service.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.repo.user.RDBMSUserRepo;
import jakarta.persistence.EntityManager;
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

    EntityManager entityManager;

    private RDBMSUserRepo userRepo;

    public List<User> findAll() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User findById(long id) {
//        System.out.println("userRepo.findById(id).get() = " + userRepo.findById(id).get());
        User user = entityManager.find(User.class, id);
//        entityManager.detach(user);
        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
//        System.out.println("user = " + user);
        return u;
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
//        User user1 = entityManager.find(User.class, id);
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

    @Override
    public List<User> findByPostsTitle(String title) {
        return userRepo.findAllByPosts_TitleEquals(title);
    }

}
