package com.example.lotspring.controller;

import com.example.lotspring.entity.User;
import com.example.lotspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String test() {
        return "hello world";
    }

    @GetMapping("/getUserById")
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/getUserList")
    public List<User> getUserList(@RequestBody User user) {
        return userService.getUserList(user);
    }

}
