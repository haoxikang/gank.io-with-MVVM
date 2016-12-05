package com.fall.gank.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by qqq34 on 2016/12/5.
 */

public class TimeUtils {
    public static String[] getTime(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(s));
        String[] strings = new String[3];
        strings[0] = calendar.get(Calendar.YEAR) + "";
        strings[1] = calendar.get(Calendar.MONTH) + 1 + "";
        strings[2] = calendar.get(Calendar.DAY_OF_MONTH) + "";
        return strings;
    }
}
