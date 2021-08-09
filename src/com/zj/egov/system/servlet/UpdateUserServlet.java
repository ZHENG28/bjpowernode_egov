package com.zj.egov.system.servlet;

import com.zj.egov.bean.User;
import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;
import com.zj.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新用户信息
 */
public class UpdateUserServlet extends HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = new User();
        WebUtil.makeRequestToObject(request, user);

        if (userService.updateUser(user)) {
            response.sendRedirect("/EGOV/servlet/pageQueryUser?pageno=" + request.getParameter("pageno"));
        } else {
            System.out.println("修改用户信息失败");
        }
    }
}
