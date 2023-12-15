package com.example.qt1.controller;

import com.example.qt1.model.User;
import com.example.qt1.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public String userPage(Model model){
        List<User> userList = service.getAllUser();
        model.addAttribute("userList", userList);
        return "User/user_manager";
    }


    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "User/add_user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User newUser) {
        newUser.setDateCreated(new Date());
        service.saveUser(newUser);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "redirect:/user";
    }

}
