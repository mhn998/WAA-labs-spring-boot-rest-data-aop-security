package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.PostV2;
import com.example.waa_first_demo.service.post.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final ModelMapper modelMapper;
    private PostService postService;

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id){
        return postService.findById(id).orElseThrow();
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
         postService.delete(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        return postService.update(id, post).orElseThrow();
    }

    @RequestMapping(headers = {"X-API-VERSION=v2"})
    public List<PostV2> findAllByAuthor(@RequestParam String author) {
        return mapToPostV2(postService.findAllByAuthor(author));
    }

    public List<PostV2> mapToPostV2(List<Post> posts) {
        return posts.stream().map(post -> modelMapper.map(post, PostV2.class)).collect(Collectors.toList());
    }




}
