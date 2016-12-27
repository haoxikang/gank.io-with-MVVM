package com.fall.gank.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by qqq34 on 2016/12/5.
 */

public class TimeUtils {
    public static final String YEAR = "TimeUtils.YEAR";
    public static final String MONTH = "TimeUtils.MONTH";
    public static final String DAY = "TimeUtils.DAY";

    public static HashMap<String, String> getTime(String s) throws ParseException {
        HashMap<String, String> hashMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(s));
        hashMap.put(YEAR, calendar.get(Calendar.YEAR) + "");
        hashMap.put(MONTH, calendar.get(Calendar.MONTH) + 1 + "");
        hashMap.put(DAY, calendar.get(Calendar.DAY_OF_MONTH) + "");

        return hashMap;
    }
}
