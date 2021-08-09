package com.zj.egov.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * 接收request工具类
 */
public class WebUtil
{
    private WebUtil() {}

    /**
     * 使用前提条件：
     * javabean的属性类型需要是String类型
     * 必须保证javabean的属性名和网页表单中的input标签的name属性值相同
     * <p>
     * 将request对象中存储的表单数据设置到javabean对象中
     *
     * @param request 含有表单数据的请求对象
     * @param obj     javabean对象
     */
    public static void makeRequestToObject(HttpServletRequest request, Object obj)
    {
        Class c = obj.getClass();
        Enumeration<String> fieldNames = request.getParameterNames();
        while (fieldNames.hasMoreElements()) {
            // 获取属性名
            String fieldName = fieldNames.nextElement();
            // 获取方法名
            String methodName = "set" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
            // 获取要调用的方法
            Method setMethod = null;
            try {
                setMethod = c.getDeclaredMethod(methodName, String.class);
                // 调用set方法
                setMethod.invoke(obj, request.getParameter(fieldName));
            } catch (Exception e) {
//                e.printStackTrace();
                // 由于存在一些字段是没有set方法，因此出现异常很正常，不需要打印异常追踪信息
            }
        }
    }
}
