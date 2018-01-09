package com.example.handle;

import com.example.exception.UserException;
import com.example.result.DataResult;
import com.example.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/9
 * @公司：汽车易生活
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public DataResult userHandle(UserException ue){
        return ResultUtil.error(ue.getStatus(),ue.getMessage());
    }
}
