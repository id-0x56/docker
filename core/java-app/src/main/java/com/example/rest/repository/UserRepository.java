package com.example.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
