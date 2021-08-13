package com.example.authentication_with_jwt.repositories;

import com.example.authentication_with_jwt.entities.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
