package com.spider.song.spidercommon.utils;

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

}
