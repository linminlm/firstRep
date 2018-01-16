package com.example.controller.user;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/15
 * @公司：汽车易生活
 */

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("/user/userList");
        mav.addObject("userList",userService.userLists());
        return mav;
    }

}
