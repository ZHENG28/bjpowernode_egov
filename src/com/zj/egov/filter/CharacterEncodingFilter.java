package com.zj.egov.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤字符集编码
 */
public class CharacterEncodingFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("json/application;charset=utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
