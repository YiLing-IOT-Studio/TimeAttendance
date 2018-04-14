package com.attendance.util;

import java.util.Date;

/**
 * Created by FantasticPan on 2018/4/13.
 */
public class TimeUtil {

    private static Long milliToHour(Long milli) {
        return milli / (60 * 60 * 1000);
    }

    private static Long milliToMinute(Long milli) {
        return milli / (60 * 1000);
    }

    private static Long milliToSecond(Long milli) {
        return milli / 1000;
    }

    private static Long hourToMilli(Long hour) {
        return hour * 60 * 60 * 1000;
    }

    private static Long minuteToMilli(Long minute) {
        return minute * 60 * 1000;
    }

    private static Long secondToMilli(Long second) {
        return second * 1000;
    }

    private static StringBuilder formatTime(Long milli) {
        StringBuilder dateStringBuilder = new StringBuilder();
        Long hour = milliToHour(milli);
        if (hour > 0) {
            dateStringBuilder.append(hour).append("时");
            milli -= hourToMilli(hour);
            Long minute = milliToMinute(milli);
            if (minute > 0) {
                dateStringBuilder.append(minute).append("分");
                milli -= minuteToMilli(minute);
                Long second = milliToSecond(milli);
                if (second > 0) {
                    dateStringBuilder.append(second).append("秒");
                }
            } else {
                Long second = milliToSecond(milli);
                if (second > 0) {
                    dateStringBuilder.append(second).append("秒");
                }
            }
        } else {
            Long minute = milliToMinute(milli);
            if (minute > 0) {
                dateStringBuilder.append(minute).append("分");
                milli -= minuteToMilli(minute);
                Long second = milliToSecond(milli);
                if (second > 0) {
                    dateStringBuilder.append(second).append("秒");
                }
            } else {
                Long second = milliToSecond(milli);
                if (second > 0) {
                    dateStringBuilder.append(second).append("秒");
                }
            }
        }
        return dateStringBuilder;
    }

    private static Long differenceMilli(Date dateOne, Date DateTwo) {
        Long dateOneMilli = dateOne.getTime();
        Long dateTwoMilli = DateTwo.getTime();
        Long differenceMilli = dateOneMilli - dateTwoMilli;
        if (differenceMilli < 0) {
            differenceMilli = -differenceMilli;
        }
        return differenceMilli;
    }

    public static StringBuilder formatTime(Date dateOne, Date dateTwo) {
        return formatTime(differenceMilli(dateOne,dateTwo));
    }
}
