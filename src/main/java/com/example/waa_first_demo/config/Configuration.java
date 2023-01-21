package com.example.waa_first_demo.config;


import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.repo.post.Imp.RDBMSPostRepo;
import com.example.waa_first_demo.repo.user.RDBMSUserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
//@AllArgsConstructor
public class Configuration {


    RDBMSPostRepo rdbmsPostRepo;
    RDBMSUserRepo rdbmsUserRepo;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Hey");
            Post p1 = new Post("Test","This is test 1","Muhannad");
            Post p2 = new Post("Test 2","This is test 2","Mugh");
            Post p3 = new Post("Test 3","This is test 3","Ameer");
            Post p4 = new Post("Test 4","This is test 4","Mohammad");
            List<Post> posts1 = new ArrayList<>() {{
                add(p1);
                add(p2);
            }};


            List<Post> posts2 = new ArrayList<>(){{
                add(p3);
                add(p4);
            }};

            rdbmsPostRepo.saveAll(posts1);
            rdbmsPostRepo.saveAll(posts2);

            User user1 = new User(1, "Muhannad");
//            user1.setPosts(posts1);

            rdbmsUserRepo.save(user1);

            User user2 = new User(2,"Mahmoud");
//            user2.setPosts(posts2);

            rdbmsUserRepo.save(user2);
        };
    }

}
