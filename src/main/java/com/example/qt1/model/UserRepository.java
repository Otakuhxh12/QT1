package com.example.qt1.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUserID(int userID);
}
