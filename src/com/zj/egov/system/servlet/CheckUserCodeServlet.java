package com.zj.egov.system.servlet;

import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 检查用户代码
 */
public class CheckUserCodeServlet extends HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String usercode = request.getParameter("usercode");
        PrintWriter out = response.getWriter();
        if (usercode != "") {
            if (userService.checkUserCode(usercode)) {
                out.println("<font color='red'>用户代码已经存在</font>");
            } else {
                out.println("<font color='green'>用户代码可以使用</font>");
            }
        } else {
            out.println("<font color='red'>用户代码不能为空</font>");
        }
    }
}
