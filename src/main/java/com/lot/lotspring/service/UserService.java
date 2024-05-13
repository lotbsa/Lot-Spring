package com.lot.lotspring.service;

import com.lot.lotspring.entity.User;

import java.util.List;

public interface UserService {

    public User getUserById(Integer id);


    List<User> getUserList(User user);

}
