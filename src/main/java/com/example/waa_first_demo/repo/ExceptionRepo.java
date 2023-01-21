package com.example.waa_first_demo.repo;

import com.example.waa_first_demo.aspect.model.Exception;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ExceptionRepo extends CrudRepository<Exception, UUID> {
}
