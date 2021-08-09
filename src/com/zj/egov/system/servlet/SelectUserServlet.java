package com.zj.egov.system.servlet;

import com.zj.egov.bean.User;
import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 选择用户
 */
public class SelectUserServlet extends HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String usercode = request.getParameter("checkboxInfo");
        User user = userService.selectUser(usercode);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/system/userUpdate.jsp").forward(request, response);
        } else {
            System.out.println("查找要修改的用户失败");
        }
    }
}
