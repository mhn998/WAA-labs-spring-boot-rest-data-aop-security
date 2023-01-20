package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository //not necessary
public interface RDBMSUserRepo extends CrudRepository<User, Long> {

    @Query(value = "SELECT * from users u \n" +
            "where (select count(*) from post p \n" +
            "  where u.id = p.user_id) > ?1  \n" +
            "order by u.id;", nativeQuery = true)
    List<User> findAllByPostsIsGreaterThan(long count);


    @Query("SELECT u FROM User u JOIN u.posts p GROUP BY u HAVING COUNT(p) > ?1")
    List<User> findByPosts_SizeGreaterThan(long size);

}
