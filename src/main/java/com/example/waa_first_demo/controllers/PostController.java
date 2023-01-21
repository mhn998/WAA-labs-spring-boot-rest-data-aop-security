package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.dto.PostDTO;
import com.example.waa_first_demo.service.comment.CommentService;
import com.example.waa_first_demo.service.post.PostService;
import com.example.waa_first_demo.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;


    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }


    @GetMapping("{id}")
    public Post findById(@PathVariable long id){
        return postService.findById(id).orElseThrow();
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
         postService.delete(id);
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable long id, @RequestBody Post post) {
        return postService.update(id, post).orElseThrow();
    }

    @GetMapping(headers = {"X-API-VERSION=v2"})
    public List<PostDTO> findAllByAuthor(@RequestParam String author) {
        List<Post> allByAuthor = postService.findAllByAuthor(author);
        return Util.mapToListOf(allByAuthor, PostDTO.class);
    }

    @GetMapping("filter")
    public List<Post> findAllPostWithTitle(@RequestParam String title) {
        return postService.findAllPostWithTitle(title);
    }

}
