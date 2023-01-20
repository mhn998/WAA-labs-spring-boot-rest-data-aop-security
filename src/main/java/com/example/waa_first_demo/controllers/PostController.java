package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.PostV2;
import com.example.waa_first_demo.service.comment.CommentService;
import com.example.waa_first_demo.service.post.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class PostController {

    private final ModelMapper modelMapper;
    private PostService postService;
    private CommentService commentService;


    // after changing url to api/v1/users/posts this is wrong now.
    // there is nothing called get all posts!
    @GetMapping("posts")
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{userId}/posts")
    public List<Post> findAllPostsByUser(@PathVariable long userId){
        return postService
                .findAllPostsByUser(userId);
    }

    @GetMapping("posts/{id}")
    public Post findById(@PathVariable long id){
        return postService.findById(id).orElseThrow();
    }

    @GetMapping("{userId}/posts/{postId}")
    public Post findPostById(@PathVariable long userId,
                             @PathVariable long postId ){
//        User user = restTemplate.getForObject("http://localhost/api/v1/users/{userId}", User.class);

        return postService
                .findPostByUser(userId, postId);
    }

    @PostMapping("{userId}/posts")
    public Post save(@PathVariable long userId, @RequestBody Post post) {
        return postService.savePostToUser(userId, post);
    }

    @DeleteMapping("posts/{id}")
    public void delete(@PathVariable int id) {
         postService.delete(id);
    }

    @PutMapping("posts/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody Post post) {
        return postService.update(id, post).orElseThrow();
    }

    @GetMapping(name = "posts", headers = {"X-API-VERSION=v2"})
    public List<PostV2> findAllByAuthor(@RequestParam String author) {
        return mapToPostV2(postService.findAllByAuthor(author));
    }

    @GetMapping("posts/filter")
    public List<Post> findAllPostWithTitle(@RequestParam String title) {
        return postService.findAllPostWithTitle(title);
    }

    public List<PostV2> mapToPostV2(List<Post> posts) {
        return posts.stream().map(post -> modelMapper.map(post, PostV2.class)).collect(Collectors.toList());
    }

}
