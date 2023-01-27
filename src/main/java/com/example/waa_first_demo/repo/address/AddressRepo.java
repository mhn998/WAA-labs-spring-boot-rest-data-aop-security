package com.example.waa_first_demo.repo.address;

import com.example.waa_first_demo.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
