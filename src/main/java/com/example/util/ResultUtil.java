package com.example.util;

import com.example.result.DataResult;

/**
 * 返回结果的工具类
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/9
 * @公司：汽车易生活
 */
public class ResultUtil {

    public static DataResult succuss(Object object){
        DataResult dataResult = new DataResult();
        dataResult.setStatus("200");
        dataResult.setMessage("操作成功");
        dataResult.setData(object);

        return dataResult;
    }

    public static DataResult succuss(){
        DataResult dataResult = new DataResult();
        dataResult.setStatus("200");
        dataResult.setMessage("操作成功");
        return dataResult;
    }

    public static DataResult error(String code,String message){
        DataResult dataResult = new DataResult();
        dataResult.setStatus(code);
        dataResult.setMessage(message);
        return dataResult;
    }
}
