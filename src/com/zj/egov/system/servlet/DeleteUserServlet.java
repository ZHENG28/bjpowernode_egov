package com.zj.egov.system.servlet;

import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除用户
 */
public class DeleteUserServlet extends HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String[] userCodes = request.getParameterValues("checkboxInfo");

        if (userService.deleteUsers(userCodes)) {
            response.sendRedirect("/EGOV/servlet/pageQueryUser");
        } else {
            System.out.println("批量删除用户失败");
        }
    }
}
