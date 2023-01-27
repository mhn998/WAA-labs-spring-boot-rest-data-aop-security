package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.dao.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository //not necessary
interface RDBMSCrudSpringUserRepoImp extends CrudRepository<UserEntity, Long> {

    // decoupled and remove public access modifier

    @Query(value = "SELECT * from users u \n" +
            "where (select count(*) from post p \n" +
            "  where u.id = p.user_id) > ?1  \n" +
            "order by u.id;", nativeQuery = true)
    List<UserEntity> findAllByPostsIsGreaterThan(long count);


    @Query("SELECT u FROM UserEntity u JOIN u.posts p GROUP BY u.id HAVING COUNT(p) >= ?1 ")
//    @Query("SELECT u FROM UserEntity u WHERE size(u.posts) >= ?1 ") //same query
    List<UserEntity> findByPosts_SizeGreaterThan(long size);

    @Query("SELECT u FROM UserEntity u JOIN u.posts p GROUP BY u.id, u.address.state HAVING COUNT(p) >= ?1 AND u.address.state=:#{state} ")
//    @Query("SELECT u FROM UserEntity u WHERE u.address.state='FL' AND size(u.posts) >= ?1 ")
    List<UserEntity> findHavingPostsGreaterThanOneBy(long size, @Param("state") String state);

    List<UserEntity> findAllByPosts_TitleEquals(@NonNull String posts_title);

    int countAllById(long id);


    // needed for security for loadUserByName in UserDetailsService
    UserEntity findByName(String name);

    UserEntity findByEmail(String email);

}
