package com.example.controller.index;

import com.example.entity.security.Member;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/2
 * @公司：汽车易生活
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping({"/","/index"})
    public String indexView(Map<String,Object> map){
        Member m = (Member) SecurityUtils.getSubject().getPrincipal();
        map.put("user",m);
        return "index";
    }
}
