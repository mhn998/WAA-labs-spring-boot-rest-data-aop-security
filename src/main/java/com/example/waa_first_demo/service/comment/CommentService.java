package com.example.waa_first_demo.service.comment;

import com.example.waa_first_demo.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {


    Optional<Comment> findById(long id);

    Comment save(Comment comment);

    Comment saveWithPost(long userId ,long postId, Comment comment);

    void delete(long id);

    Optional<Comment> update(long id, Comment p);

    List<Comment> findAllCommentsByUserAndPost(long userId, long postId);

    Comment findComment(long userId,long postId, long commentId);

}
