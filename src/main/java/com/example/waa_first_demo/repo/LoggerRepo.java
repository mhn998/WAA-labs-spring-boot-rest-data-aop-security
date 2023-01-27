package com.example.waa_first_demo.repo;

import com.example.waa_first_demo.aspect.model.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoggerRepo extends CrudRepository<Logger, UUID> {
}
