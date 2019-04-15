package com.example.advice;

import com.example.Enum.UserEnum;
import com.example.entity.nosql.ApiMonitorInfo;
import com.example.entity.user.PubUser;
import com.example.exception.UserException;
import com.example.result.DataResult;
import com.example.service.ApiMonitorInfoService;
import com.example.service.PubUserService;
import com.example.util.DateUtil;
import com.example.util.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目：test
 * @author ：linmin
 * @创建时间：2017/12/27
 */

@Aspect
@Component
public class HttpAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiMonitorInfoService apiMonitorInfoService;

    @Autowired
    private PubUserService pubUserService;

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
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
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
            log.warn("有人在晚间调用接口!");
            throw new UserException(UserEnum.CANT_FAIL);
        }

        //check publicKey of pubUser
        Object[] args = joinPoint.getArgs();
        String username = null;
        String publicKey = null;
        try{
            //get username
            username = (String)args[0];
            //get publicKey
            publicKey = (String)args[1];
        }catch(Exception e){
            log.error("调用接口失败原因：默认参数不对！");
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }

        PubUser onePubUser = pubUserService.getOnePubUser(username);
        if(onePubUser == null || !DigestUtils.md5Hex(onePubUser.getPrivateKey()).equals(publicKey) || !onePubUser.getStatus().equals("01")){
            log.error("调用接口失败原因：默认参数不对！");
            throw new UserException(UserEnum.UNKNOWN_FAIL);
        }

        //get request to get session
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

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
