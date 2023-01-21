package com.example.waa_first_demo.repo.comment;

import com.example.waa_first_demo.domain.Comment;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPost_UserIdAndPostId(@NonNull long user_id, @NonNull long post_id);

    Comment findByPost_UserIdAndPostIdAndId(@NonNull long userId, long postId, long commentId);

}
