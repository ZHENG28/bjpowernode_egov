package com.zj.egov.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类
 */
public class DateUtil
{
    private DateUtil() {}

    public static String format(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
}
