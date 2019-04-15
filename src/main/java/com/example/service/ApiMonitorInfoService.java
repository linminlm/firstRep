package com.example.service;

import com.example.entity.nosql.ApiMonitorInfo;

/**
 * @api功能
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/11
 */
public interface ApiMonitorInfoService {

    /**
     * 更新mongodb中此次API调用信息
     * @param apiMonitorInfo
     */
    void saveApiMoniInfo(ApiMonitorInfo apiMonitorInfo);

}
