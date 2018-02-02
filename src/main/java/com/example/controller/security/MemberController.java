package com.example.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统成员Controller
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/30
 * @公司：汽车易生活
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    @RequestMapping("/add")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("member/add");
        return mav;
    }
}
