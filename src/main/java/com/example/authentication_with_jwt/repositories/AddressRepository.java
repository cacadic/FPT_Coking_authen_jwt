package com.example.authentication_with_jwt.repositories;

import com.example.authentication_with_jwt.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findByWard(String ward);
}
