package com.example.test.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.test.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
