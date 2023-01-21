package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Comment;
import com.example.waa_first_demo.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class CommentController {

    CommentService commentService;


    @PostMapping("{userId}/posts/{postId}/comments")
    public Comment createComment( @RequestBody Comment comment , @PathVariable long userId, @PathVariable long postId) {
        return commentService.saveWithPost(userId, postId, comment);
    }

    @GetMapping("{userId}/posts/{postId}/comments")
    public List<Comment> findAllCommentsOnPost(@PathVariable long userId, @PathVariable long postId) {
        return commentService.findAllCommentsByUserAndPost(userId, postId);
    }

    @GetMapping("{userId}/posts/{postId}/comments/{commentId}")
    public Comment findAllCommentsOnPost(@PathVariable long userId, @PathVariable long postId, @PathVariable long commentId) {
        return commentService.findComment(userId, postId, commentId);
    }

}
