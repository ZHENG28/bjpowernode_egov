package com.zj.egov.util;

/**
 * 处理字符串工具类
 */
public class StringUtil
{
    private StringUtil() {}

    public static boolean isNotEmpty(String str)
    {
        return str != null && str.trim().length() != 0;
    }

    public static String changePage(int pageno)
    {
        return "style='cursor: pointer' onclick='changePage(" + pageno + ")'";
    }
}
