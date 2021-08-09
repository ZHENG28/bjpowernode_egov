package com.zj.egov.util;

import java.util.ResourceBundle;

/**
 * 绑定资源工具类
 */
public class RBUtil
{
    // 类加载时绑定资源
    public static ResourceBundle bundle = ResourceBundle.getBundle("com.zj.egov.resource.Message");

    private RBUtil() {}

    // 通过配置文件的key获取其value值
    public static String getStringByResource(String code)
    {
        return bundle.getString(code);
    }

    // 通过配置文件的key获取其value值(加上前缀)
    public static String getStringByResource(String pre, String code)
    {
        return bundle.getString(pre + code);
    }
}
