package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */
public interface UserService {
    List<User> userLists();

    User addUser(User user);

    void delUser(Integer id);

    User updUser(User user);
}
