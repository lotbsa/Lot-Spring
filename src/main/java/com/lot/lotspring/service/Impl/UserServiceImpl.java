package com.lot.lotspring.service.Impl;

import com.lot.lotspring.dao.UserDao;
import com.lot.lotspring.entity.User;
import com.lot.lotspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUserList(User user) {
        return userDao.getUserList(user);
    }

}
