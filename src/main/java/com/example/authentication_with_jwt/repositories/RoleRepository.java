package com.example.authentication_with_jwt.repositories;

import com.example.authentication_with_jwt.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByTitle(String role);

}
