package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object list(){
        List<User> users = userService.userLists();
        return users;
    }

    @PostMapping("/addUser")
    public Object addUser(User user){
        User user1 = userService.addUser(user);
        return user1;
    }

    @GetMapping("/delUser/{id}")
    public Object delUser(@PathVariable("id") Integer id){
        userService.delUser(id);
        return null;
    }

    @PutMapping("/updUser")
    public Object updUser(User user){
        return userService.updUser(user);
    }
}
