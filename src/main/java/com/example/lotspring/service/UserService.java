package com.example.lotspring.service;

import com.example.lotspring.entity.User;

import java.util.List;

public interface UserService {

    public User getUserById(Integer id);


    List<User> getUserList(User user);

}
