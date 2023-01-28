package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.security.UserDetailsCustom;
import com.example.waa_first_demo.repo.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserDetailsServiceCustomized implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepo.finByEmail(username));

        return user.map(UserDetailsCustom::new).get();
    }
}
