package com.example.controller.api;

import com.example.entity.user.User;
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
@RequestMapping("/userApi")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object list(String username,String key){
        List<User> users = userService.userLists();
        return users;
    }

    @PostMapping("/addUser")
    public Object addUser(String username,String key,User user){
        User user1 = userService.addUser(user);
        return user1;
    }

    @GetMapping("/delUser/{id}")
    public Object delUser(String username,String key,@PathVariable("id") Integer id){
        userService.delUser(id);
        return null;
    }

    @PutMapping("/updUser")
    public Object updUser(String username,String key,User user){
        return userService.updUser(user);
    }
}
