package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.dao.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository //not necessary
public interface RDBMSCrudSpringUserRepoImp extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT * from users u \n" +
            "where (select count(*) from post p \n" +
            "  where u.id = p.user_id) > ?1  \n" +
            "order by u.id;", nativeQuery = true)
    List<UserEntity> findAllByPostsIsGreaterThan(long count);


    @Query("SELECT u FROM UserEntity u JOIN u.posts p GROUP BY u HAVING COUNT(p) > ?1")
    List<UserEntity> findByPosts_SizeGreaterThan(long size);

    List<UserEntity> findAllByPosts_TitleEquals(@NonNull String posts_title);

}
