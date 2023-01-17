package com.example.waa_first_demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    long id;
    String title;
    String content;
    String author;

    public Post(Post post) {
        this.id = post.id;
        this.title = post.title;
        this.content = post.content;
        this.author = post.author;
    }

    public void setPost(Post post) {
        this.id = post.id;
        this.title = post.title;
        this.content = post.content;
        this.author = post.author;
    }



}
