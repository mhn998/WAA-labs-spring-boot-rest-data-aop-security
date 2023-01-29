package com.example.waa_first_demo.repo.security;

import com.example.waa_first_demo.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepo extends CrudRepository<UserToken,Long> {
}
