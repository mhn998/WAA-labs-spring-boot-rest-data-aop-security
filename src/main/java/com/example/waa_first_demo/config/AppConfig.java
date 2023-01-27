package com.example.waa_first_demo.config;


import com.example.waa_first_demo.domain.Role;
import com.example.waa_first_demo.domain.dao.UserEntity;
import com.example.waa_first_demo.repo.RoleRepo;
import com.example.waa_first_demo.repo.post.Imp.RDBMSPostRepo;
import com.example.waa_first_demo.repo.user.UserRepo;
import com.example.waa_first_demo.repo.user.UserRepoSpringJPAImp;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

@Configuration
@AllArgsConstructor
//@Transactional
public class AppConfig {


    RDBMSPostRepo rdbmsPostRepo;
    UserRepo rdbmsCrudSpringUserRepoImp;


    // newly added for testing adding user and roles directly
    UserRepoSpringJPAImp userRepoSpringJPAImp;

    RoleRepo roleRepo;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
//    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

            UserEntity muhannad = new UserEntity("Muhannad", "mugh@k.com", "pass1", true);
            muhannad.setPassword(getEncodedPassword(muhannad.getPassword()));
            UserEntity mahmoud = new UserEntity("Mahmoud", "mahmdoud@k.com" , "pass2", true);
            mahmoud.setPassword(getEncodedPassword(mahmoud.getPassword()));

            System.out.println("muhannad = " + muhannad);
            System.out.println("mahmoud = " + mahmoud);

            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");

            userRepoSpringJPAImp.save(muhannad);
            userRepoSpringJPAImp.save(mahmoud);

            admin.setUser(muhannad);
            user.setUser(mahmoud);

            muhannad.setRoles(List.of(admin));
            mahmoud.setRoles(List.of(user));

            roleRepo.save(admin);
            roleRepo.save(user);

        };
    }

    private static String getEncodedPassword(String plainPassword) {
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());

        return bCryptPasswordEncoder.encode(plainPassword);
    }

}
