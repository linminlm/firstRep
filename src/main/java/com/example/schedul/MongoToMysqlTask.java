package com.example.schedul;

import com.example.dao.ApiUserHistoryDao;
import com.example.entity.history.ApiUserHistoryEntity;
import com.example.entity.nosql.ApiMonitorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @定时任务之从mongodb中将数据迁移到mysql并清空mongodb中的数据
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/16
 * @公司：汽车易生活
 */

@Component
public class MongoToMysqlTask {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApiUserHistoryDao apiUserHistoryDao;

    /**
     * 定时任务：清除mongodb 转储mysql
     * 时间：0 0 0 * * ? 每天凌晨0点开始
     */
    @Scheduled(cron="0 0 0 * * ?")
    private void dataMove(){
        List<ApiMonitorInfo> list = mongoTemplate.findAll(ApiMonitorInfo.class);
        for(ApiMonitorInfo ami:list){
            ApiUserHistoryEntity apiUserHistory = new ApiUserHistoryEntity();
            apiUserHistory.setControllerName(ami.getControllerName());
            apiUserHistory.setLastDate(new Date());
            apiUserHistory.setMethodName(ami.getMethodName());
            apiUserHistory.setLastNum(ami.getCounter());
            apiUserHistory.setUserIP(ami.getUserIP());
            ApiUserHistoryEntity ahe = apiUserHistoryDao.findByCnameAndIpAndMname(ami.getControllerName(), ami.getUserIP(), ami.getMethodName());
            //判断数据库中是否有该用户调用该方法的记录。
            if(ahe != null){
                //如果有则修改数据。保存
                ahe.setLastNum(ahe.getTotalNum());
                ahe.setPreDate(ahe.getLastDate());
                ahe.setLastNum(ami.getCounter());
                ahe.setLastDate(new Date());
                ahe.setTotalNum(ahe.getTotalNum()+ami.getCounter());
                apiUserHistoryDao.save(ahe);
            }else{
                //如果没有则新增新记录
                apiUserHistoryDao.save(apiUserHistory);
            }
            //清库mongodb数据
            mongoTemplate.dropCollection(ApiMonitorInfo.class);
        }
    }

}
