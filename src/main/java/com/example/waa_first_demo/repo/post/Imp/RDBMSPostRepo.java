package com.example.waa_first_demo.repo.post.Imp;

import com.example.waa_first_demo.domain.Post;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("rdbms")
public interface RDBMSPostRepo extends CrudRepository<Post, Long> {

}
