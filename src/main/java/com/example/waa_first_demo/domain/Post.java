package com.example.waa_first_demo.domain;

import com.example.waa_first_demo.domain.dao.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity // first step
@RequiredArgsConstructor
public class Post {

    // second step
    @Id
    @GeneratedValue
    long id;

    @NonNull
    String title;

    @NonNull
    String content;

    @NonNull
    String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    UserEntity user;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    @JsonManagedReference
    List<Comment> comments;


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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
