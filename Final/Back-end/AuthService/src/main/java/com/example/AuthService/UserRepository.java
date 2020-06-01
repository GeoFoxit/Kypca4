package com.example.AuthService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User getById(Integer id);
    User getByEmail(String email);
}