package com.example.service.impl;

import com.example.entity.nosql.ApiMonitorInfo;
import com.example.service.ApiMonitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/11
 * @公司：汽车易生活
 */
public class ApiMonitorInfoServiceImpl implements ApiMonitorInfoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveApiMoniInfo(ApiMonitorInfo apiMonitorInfo) {
        Query query=new Query();
        query.addCriteria(Criteria.where("methodName").is(apiMonitorInfo.getMethodName()));
        query.addCriteria(Criteria.where("controllerName").is(apiMonitorInfo.getControllerName()));
        //query.addCriteria(Criteria.where("userIP").is())
    }
}