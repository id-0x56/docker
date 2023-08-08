package com.example.rest.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.rest.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
