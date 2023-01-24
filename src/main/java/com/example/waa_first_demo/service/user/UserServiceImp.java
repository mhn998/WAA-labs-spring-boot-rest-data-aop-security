package com.example.waa_first_demo.service.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.dao.UserEntity;
import com.example.waa_first_demo.repo.user.UserRepo;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
class UserServiceImp implements UserService {

    // here in the service the data persistence methods that is allowed to use only what is provided by userRepo, not the whole spring implementation of Crud
    // service implementation is decoupled and defined as private-package
    EntityManager entityManager;

    private UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(long id) {
        UserEntity user = entityManager.find(UserEntity.class, id); // just for demo service should not talk to entity manager directly
        if(user == null) throw new RuntimeException("User not found!");
        entityManager.detach(user);
        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
        System.out.println("user = " + user);
        return u;
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void delete(long id) {
         userRepo.delete(id);
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
        return userRepo.findByPostsTitle(title);
    }

    @Override
    public Page<User> loadAll(Pageable pageable) {
        return userRepo.loadUsers(pageable);
    }

}
