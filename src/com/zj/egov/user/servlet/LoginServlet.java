package com.zj.egov.user.servlet;

import com.zj.egov.bean.User;
import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.user.service.LogService;
import com.zj.egov.user.service.impl.LogServiceImpl;
import com.zj.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录系统
 */
public class LoginServlet extends HttpServlet
{
    private LogService logService = new LogServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orgtype = request.getParameter("orgtype");
        String usercode = request.getParameter("usercode");
        String userpwd = request.getParameter("userpwd");

        User user = logService.login(orgtype, usercode, userpwd);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/EGOV/main.html");
        } else {
            request.setAttribute("errMsg", "用户名或密码错误，请重新填写");
            // 转发到欢迎界面
            request.getRequestDispatcher("/").forward(request, response);
        }
    }
}
