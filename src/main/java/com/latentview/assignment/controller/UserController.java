package com.latentview.assignment.controller;

import com.latentview.assignment.entity.UserData;
import com.latentview.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public UserData createUser(@RequestBody UserData userData){
        return userService.saveUser(userData);
    }

    @PostMapping("authenticate")
    public UserData getUser(@RequestBody UserData userData){
        return userService.userAuthenticate(userData);
    }

}
