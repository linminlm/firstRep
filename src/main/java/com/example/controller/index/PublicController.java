package com.example.controller.index;

import com.example.entity.security.Member;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
@Controller
@RequestMapping("/public")
public class PublicController {
    @RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String loginUser(@ModelAttribute("member") Member member,String rememberMe, HttpServletRequest request) {
        Boolean rem = rememberMe != null&&rememberMe.equals("yes")?true:false;
        String memberName = request.getParameter("memberName");
        String password = request.getParameter("password");
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(member.getMemberName(),member.getPassword(),rem);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            //TODO
            //Member member=(Member) subject.getPrincipal();
            return "index";
        } catch(Exception e) {
            return "/public/login";//返回登录页面
        }

    }

    @RequestMapping("/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/public/login";
    }

    @RequestMapping("/notAuth")
    public String notAuthView(){
        return "public/notAuth";
    }
}
