package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoSpringJPAImp extends JpaRepository<UserEntity, Long> {
}
