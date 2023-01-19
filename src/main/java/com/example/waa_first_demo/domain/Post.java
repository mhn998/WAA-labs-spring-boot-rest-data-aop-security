package com.example.waa_first_demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // first step
public class Post {


    // second step
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    String title;
    String content;
    String author;

//    @ManyToOne
//    User user;

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
