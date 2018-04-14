package com.attendance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FantasticPan on 2018/4/13.
 */
public class DateUtil {

    public static Date formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = new SimpleDateFormat(date).format(new Date());
        String year = dateString.substring(0,4);
        String month = dateString.substring(dateString.indexOf("年")+1,dateString.indexOf("月"));
        String day = dateString.substring(dateString.indexOf("月")+1,dateString.indexOf("日"));
        return sdf.parse(year+"-"+month+"-"+day);
    }
}
