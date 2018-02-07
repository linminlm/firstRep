package com.example.controller.index;

import com.example.entity.security.Member;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public String loginUser(Member member, HttpServletRequest request,Map<String,Object> map) {
        UsernamePasswordToken token = new UsernamePasswordToken(member.getMemberName(), member.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
            return "/index";
        } catch (AuthenticationException e) {
            map.put("msg","用户名或密码错误");
            return "/public/login";
        }

    }

    @RequestMapping(value="/loginView")
    public String loginView(){
        return "/public/login";
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
