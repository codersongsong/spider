package com.spider.song.spidercommon.utils;

import com.spider.song.spidercommon.date.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil(){
        //keep from 外部实例化
    }

    public static Date parseDateStr(String dateStr,String dateStrPattern) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(dateStrPattern);
        return  sdf.parse(dateStr);

    }


    public static String formatDate(Date date,String dateStrPattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateStrPattern);
        return sdf.format(date);


    }


    public static String formatDateStr(String value,String oldPattern,String newPattern) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);

        Date date = sdf1.parse(value);
        return sdf2.format(date);

    }

    /**
     * 距离今天结束还剩多少秒
     * @return
     */
    public static int leftSecondsToday(){
        String startTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");//正确格式：2012-12-12 12:12:
        String endTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd")+" 23:59:59";
        int seconds = DateUtils.compareTime(startTime, endTime, 3);

        return seconds;
    }

}
