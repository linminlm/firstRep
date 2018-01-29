package com.example.controller.user;


import com.example.Enum.UserEnum;
import com.example.entity.user.PubUser;
import com.example.exception.UserException;
import com.example.result.DataResult;
import com.example.service.MailService;
import com.example.service.PubUserService;
import com.example.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pubUser")
public class PubUserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PubUserService pubUserService;

    @Autowired
    private MailService mailService;

    @GetMapping("/addPubUser")
    public PubUser addPubUser(PubUser pubUser){
        try {
            log.info("添加公共用户成功!");
            PubUser pubUser1 = pubUserService.addPubUser(pubUser);
            //send mail
            return pubUser1;
        } catch (Exception e) {
            log.error("添加公共用户失败!");
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }
    }

    @GetMapping("/activePubUser")
    public DataResult activePubUser(String checkCode,String username){
        pubUserService.activeEmail(checkCode,username);
        return ResultUtil.succuss();
    }
}
