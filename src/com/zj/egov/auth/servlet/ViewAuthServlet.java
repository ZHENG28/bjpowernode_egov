package com.zj.egov.auth.servlet;

import com.zj.egov.auth.service.AuthService;
import com.zj.egov.auth.service.impl.AuthServiceImpl;
import com.zj.egov.bean.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查看核准件
 */
public class ViewAuthServlet extends HttpServlet
{
    private AuthService authService = new AuthServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String authno = request.getParameter("authno");
        Auth auth = authService.findByAuthno(authno);
        if (auth != null) {
            request.setAttribute("auth", auth);
            request.getRequestDispatcher("/authresponse/authResponseView.jsp").forward(request, response);
        } else {
            response.getWriter().println("<font style='color:red' class='STYLE1'>未找到核准件</font>");
        }
    }
}
