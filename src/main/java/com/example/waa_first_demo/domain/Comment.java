package com.example.waa_first_demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    long id;

    String name;

    @ManyToOne
    @JsonBackReference
    Post post;


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
