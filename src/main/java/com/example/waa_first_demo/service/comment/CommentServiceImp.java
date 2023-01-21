package com.example.waa_first_demo.service.comment;

import com.example.waa_first_demo.domain.Comment;
import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.repo.comment.CommentRepo;
import com.example.waa_first_demo.repo.post.Imp.RDBMSPostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {

    CommentRepo commentRepo;
    RDBMSPostRepo postRepo;

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepo.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public Comment saveWithPost(long userId, long postId, Comment comment) {
        Post postToAddComment = postRepo.findByPost_IdEquals(userId, postId);

        if(postToAddComment != null) {
            postToAddComment.getComments().add(comment);
        } else {
            throw new RuntimeException("User not found!");
        }

        commentRepo.save(comment);
        postRepo.save(postToAddComment);

        return comment;
    }


    @Override
    public void delete(long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public Optional<Comment> update(long id, Comment p) {
        Optional<Comment> byId = commentRepo.findById(id);

        byId.ifPresent((comment) -> {
            comment.setId(p.getId());
            comment.setName(p.getName());
        });

        return byId;
    }

    @Override
    public List<Comment> findAllCommentsByUserAndPost(long userId, long postId) {
        return commentRepo.findAllByPost_UserIdAndPostId(userId, postId);
    }

    @Override
    public Comment findComment(long userId, long postId, long commentId) {
        return commentRepo.findByPost_UserIdAndPostIdAndId(userId, postId, commentId);
    }

}
