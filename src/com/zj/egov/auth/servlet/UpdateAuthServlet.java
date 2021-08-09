package com.zj.egov.auth.servlet;

import com.zj.egov.auth.service.AuthService;
import com.zj.egov.auth.service.impl.AuthServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新核准件信息
 */
public class UpdateAuthServlet extends HttpServlet
{
    private AuthService authService = new AuthServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String authno = request.getParameter("authno");
        if (authService.feedback(authno)) {
            response.sendRedirect("/EGOV/authresponse/authResponseList.jsp");
        } else {
            System.out.println("反馈核准件失败");
        }
    }
}
