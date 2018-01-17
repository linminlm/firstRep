package com.example.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @时间工具类
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/16
 * @公司：汽车易生活
 */
public class DateUtil {
    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
