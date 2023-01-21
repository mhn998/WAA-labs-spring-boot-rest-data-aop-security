package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Comment;
import com.example.waa_first_demo.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class CommentController {

    CommentService commentService;

}
