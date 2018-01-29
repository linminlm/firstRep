package com.example.controller.user;


import com.example.Enum.UserEnum;
import com.example.entity.user.PubUser;
import com.example.exception.UserException;
import com.example.result.DataResult;
import com.example.service.PubUserService;
import com.example.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pubUser")
public class PubUserController {
    @Autowired
    private PubUserService pubUserService;

    @GetMapping("/addPubUser")
    public PubUser addPubUser(PubUser pubUser){
        try {
            return pubUserService.addPubUser(pubUser);
        } catch (Exception e) {
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }
    }

    @GetMapping("/activePubUser")
    public DataResult activePubUser(String checkCode,String username){
        pubUserService.activeEmail(checkCode,username);
        return ResultUtil.succuss();
    }
}
