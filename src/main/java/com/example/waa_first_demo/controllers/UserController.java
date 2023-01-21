package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

//    private RestTemplate restTemplate;

    @GetMapping
    public List<User> findAll(){
        return userService
                .findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id){
        return userService
                .findById(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        System.out.println("hello<!>");
        return userService
                .save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService
                .delete(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService
                .update(id, user).orElseThrow();
    }


    @GetMapping("filter")
    public List<User> findAllUserThatHaveMoreThanPosts(@RequestParam Long postsCountGreaterThan){
            return userService
                .findByPosts_SizeGreaterThan(postsCountGreaterThan);
    }

    @GetMapping("filterPostTitle")
    public List<User> findAllUsersWithPostTitle(@RequestParam String postTitle){
        return  userService
                .findByPostsTitle(postTitle);
    }


}
