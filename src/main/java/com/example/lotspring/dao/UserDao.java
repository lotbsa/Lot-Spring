package com.example.lotspring.dao;

import com.example.lotspring.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    User getUserById(Integer id);

    List<User> getUserList(User user);

}
