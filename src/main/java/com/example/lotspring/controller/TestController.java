package com.example.lotspring.controller;

import com.example.lotspring.common.ResponseVO;
import com.example.lotspring.entity.User;
import com.example.lotspring.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public ResponseVO<String> test() {
        return ResponseVO.success("hello world");
    }

    @GetMapping("/getUserById")
    public ResponseVO<User> getUserById(Integer id) {
        return ResponseVO.success(userService.getUserById(id));
    }

    @PostMapping("/getUserList")
    public ResponseVO<List<User>> getUserList(@RequestBody @Validated  User user) {
        return ResponseVO.success(userService.getUserList(user));
    }

}
