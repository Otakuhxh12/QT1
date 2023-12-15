package com.example.qt1.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUser(){
        return (List<User>) repo.findAll();
    }


    public void saveUser(User newUser) {
        repo.save(newUser);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public User getUserById(int userID) {
        return repo.findByUserID(userID);
    }
}
