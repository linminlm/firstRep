package com.example.service;

import com.example.entity.user.User;

import java.util.List;

/**
 * @用户Service
 * @项目：test
 * @author ：linmin
 * @创建时间：2017/12/27
 */
public interface UserService {
    List<User> userLists();

    User getOneUser(Integer id);

    User addUser(User user);

    void delUser(Integer id);

    User updUser(User user);
}
