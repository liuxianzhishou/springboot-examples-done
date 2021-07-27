package com.yh.mybatisgenwebsocket.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //精确到秒
    private static DateFormat dfYs=new SimpleDateFormat("yyyyMMddHHmmss");
    //精确到毫秒
    private static SimpleDateFormat dfYsS=new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     *获取当前时间
     */
    public static Date getNowTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * @return yyyyMMddHHmmss格式String类型当前时间
     */
    public static String getYsDate()
    {
        Calendar calendar=Calendar.getInstance();
        String ysDate=dfYs.format(calendar.getTime());
        return ysDate;
    }

    /**
     * @return yyyyMMddHHmmss格式String类型当前时间前几秒的时间
     */
    public static String getYsDateAgo(int millisecond)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND,millisecond);
        String ysDate=dfYs.format(calendar.getTime());
        return ysDate;
    }

    /**
     * @return yyyyMMddHHmmssSSS格式String类型当前时间
     */
    public static String getYsSDate()
    {
        Calendar calendar=Calendar.getInstance();
        String ysDate=dfYsS.format(calendar.getTime());
        return ysDate;
    }

    /**
     * @return yyyyMMddHHmmssSSS格式String类型当前时间前几秒的时间
     */
    public static String getYsSDateAgo(int millisecond)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND,millisecond);
        String tmp=dfYsS.format(calendar.getTime());
        String ysSDate=tmp.substring(0,15);
//        System.out.println("substring:"+ysSDate);
        return ysSDate;
    }
}
