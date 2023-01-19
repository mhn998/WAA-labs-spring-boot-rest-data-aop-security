package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService
                .findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService
                .findById(id).orElseThrow();
    }

    @PostMapping
    public User save(User user) {
        return userService
                .save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService
                .delete(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User post) {
        return userService
                .update(id, post).orElseThrow();
    }


    @GetMapping("/{id}/posts")
    public List<Post> findAllPostsByUser(@PathVariable long id){
        return userService
                .findAllPostsByUserId(id);
    }

    @GetMapping("filter")
    public List<User> findAllUserThatHaveMoreThanPosts(@RequestParam long postsCountGreaterThan){
        return userService
                .findByPosts_SizeGreaterThan(postsCountGreaterThan);
    }




}
