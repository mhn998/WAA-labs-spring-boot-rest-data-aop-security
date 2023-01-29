package com.example.waa_first_demo.repo.security;

import com.example.waa_first_demo.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String token);

}
