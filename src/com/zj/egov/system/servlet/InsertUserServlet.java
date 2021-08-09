package com.zj.egov.system.servlet;

import com.zj.egov.bean.User;
import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;
import com.zj.egov.util.Const;
import com.zj.egov.util.DateUtil;
import com.zj.egov.util.WebUtil;

import java.io.IOException;
import java.util.Date;

/**
 * 新增用户
 */
public class InsertUserServlet extends javax.servlet.http.HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
    {
        // 获取用户信息
        User user = new User();
        WebUtil.makeRequestToObject(request, user);
        String regdate = DateUtil.format(new Date(), Const.DATE_FORMAT_ALL);
        user.setRegdate(regdate);

        // 调用Model
        int retValue = userService.insertUser(user);

        // 调用View
        if (retValue == -1) {
            request.setAttribute("errMsg", "用户代码已存在，请重新填写");
            request.getRequestDispatcher("/system/userAdd.jsp").forward(request, response);
        } else if (retValue == 1) {
            response.sendRedirect("/EGOV/servlet/pageQueryUser");
        } else if (retValue == 0) {
            System.out.println("新增用户信息失败");
        }
    }
}
