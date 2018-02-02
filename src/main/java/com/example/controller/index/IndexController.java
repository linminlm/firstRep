package com.example.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String indexView(){
        return "index";
    }
}
