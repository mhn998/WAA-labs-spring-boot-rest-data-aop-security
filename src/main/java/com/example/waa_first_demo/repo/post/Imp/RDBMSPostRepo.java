package com.example.waa_first_demo.repo.post.Imp;

import com.example.waa_first_demo.domain.Post;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("rdbms")
public interface RDBMSPostRepo extends CrudRepository<Post, Long> {

    List<Post> findAllByTitleEqualsIgnoreCase(@NonNull String title);

    List<Post> findAllByUserId(@NonNull long user_id);

    @Query("SELECT p FROM Post p JOIN p.user u where p.id = ?2 AND u.id = ?1")
    Post findByPost_IdEquals(long userId, long postId);

}
