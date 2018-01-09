package com.example.advice;

import com.example.result.DataResult;
import com.example.util.RedisUtil;
import com.example.util.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */

@Aspect
@Component
public class HttpAdvice {

    @Pointcut("execution(public * com.example.controller.UserController.*(..)))")
    public void userCut(){}

    /**
     *  对api的使用进行统计
     * @param joinPoint
     */
    @Before("userCut()")
    public void beforeRequest(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //get session to get user info
        HttpSession session = request.getSession();

        //user compared to userdata of mysql.    not available

        //get IP of user
        String userIP = request.getRemoteAddr();

        //get Controller className
        String controllerName = joinPoint.getTarget().getClass().getName();

        //get method name
        String methodName = joinPoint.getSignature().getName();

        //Connect redis to save info
        Jedis jedis = RedisUtil.getJedis();

        //save data => key:ip value:"ip:xxx.xxx.xxx.xxx | className:xxx | method:xxx | count:x"
        int count = 1;
        if(jedis.hexists(userIP, controllerName + "." + methodName)){
            String countStr = jedis.hget(userIP, controllerName + "." + methodName).toString();
            count = Integer.parseInt(countStr)+1;
        }
        Map<String, String> map = new HashMap<>();
        map.put(controllerName+"."+methodName,count+"");
        jedis.hmset(userIP,map);
    }


    /**
     * 对返回值再次封装
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("userCut()")
    public DataResult resData(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();

        return ResultUtil.succuss(object);
    }

}
