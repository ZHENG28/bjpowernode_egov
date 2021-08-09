package com.zj.egov.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 检查是否登录
 */
public class CheckLoginFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        if ("/login.jsp".equals(servletPath) || "/servlet/login".equals(servletPath) || (session != null && session.getAttribute("user") != null)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/EGOV/");
        }
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
