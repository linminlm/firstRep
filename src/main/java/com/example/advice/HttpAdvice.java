package com.example.advice;

import com.example.Enum.UserEnum;
import com.example.entity.nosql.ApiMonitorInfo;
import com.example.exception.UserException;
import com.example.result.DataResult;
import com.example.service.ApiMonitorInfoService;
import com.example.util.DateUtil;
import com.example.util.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2017/12/27
 * @公司：汽车易生活
 */

@Aspect
@Component
public class HttpAdvice {

    @Autowired
    private ApiMonitorInfoService apiMonitorInfoService;

    @Pointcut("execution(public * com.example.controller.api.ApiUserController.*(..)))")
    public void userApiCut(){}

    /**
     *  对api的使用进行统计
     *  存放在redis中
     * @param joinPoint
     */
//    @Before("userCut()")
//    public void beforeRequest(JoinPoint joinPoint){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        //get session to get user info
//        HttpSession session = request.getSession();
//
//        //user compared to userdata of mysql.    not available
//
//        //get IP of user
//        String userIP = request.getRemoteAddr();
//
//        //get Controller className
//        String controllerName = joinPoint.getTarget().getClass().getName();
//
//        //get method name
//        String methodName = joinPoint.getSignature().getName();
//
//        //Connect redis to save info
//        Jedis jedis = RedisUtil.getJedis();
//
//        //save data => key:ip value:"ip:xxx.xxx.xxx.xxx | className:xxx | method:xxx | count:x"
//        int count = 1;
//        if(jedis.hexists(userIP, controllerName + "." + methodName)){
//            String countStr = jedis.hget(userIP, controllerName + "." + methodName).toString();
//            count = Integer.parseInt(countStr)+1;
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put(controllerName+"."+methodName,count+"");
//        jedis.hmset(userIP,map);
//    }


    /**
     * 对返回值再次封装
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("userApiCut()")
    public DataResult resData(ProceedingJoinPoint joinPoint) throws Throwable {
        //Data updates are not allowed to be invoked.(00:00 - 03:00)
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date now =null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse("00:00");
            endTime = df.parse("03:00");
        } catch (Exception e) {
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }

        Boolean flag = DateUtil.belongCalendar(now, beginTime, endTime);
        if(flag){
            throw new UserException(UserEnum.CANT_FAIL);
        }

        //get request to get session
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
        //api计数器存放在mongdb中
        ApiMonitorInfo apiInfo = new ApiMonitorInfo();
        apiInfo.setControllerName(controllerName);
        apiInfo.setMethodName(methodName);
        apiInfo.setUserIP(userIP);

        apiMonitorInfoService.saveApiMoniInfo(apiInfo);

        Object object = joinPoint.proceed();

        return ResultUtil.succuss(object);
    }

}
