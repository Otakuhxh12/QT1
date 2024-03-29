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

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable int id, Model model) {
        User existingUser = service.getUserById(id);
        model.addAttribute("existingUser", existingUser);
        return "User/update_user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User updatedUser) {
        User existingUser = service.getUserById(id);

        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setMobilePhone(updatedUser.getMobilePhone());
        existingUser.setActive(updatedUser.isActive());

        existingUser.setDateCreated(existingUser.getDateCreated());

        service.saveUser(existingUser);
        return "redirect:/user";
    }

}
