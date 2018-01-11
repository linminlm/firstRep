package com.example.service;

import com.example.entity.nosql.ApiMonitorInfo;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/11
 * @公司：汽车易生活
 */
public interface ApiMonitorInfoService {

    /**
     * 更新mongodb中此次API调用信息
     * @param apiMonitorInfo
     */
    void saveApiMoniInfo(ApiMonitorInfo apiMonitorInfo);

}
