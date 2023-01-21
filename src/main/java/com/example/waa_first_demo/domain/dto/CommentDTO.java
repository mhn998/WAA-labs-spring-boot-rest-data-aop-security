package com.example.waa_first_demo.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class CommentDTO {
    long id;
    String name;

}
