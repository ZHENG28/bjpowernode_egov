package com.zj.egov.system.servlet;

import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;
import com.zj.egov.system.service.UserService;
import com.zj.egov.system.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分页查询用户
 */
public class PageQueryUserServlet extends HttpServlet
{
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 获取页码
        String pageno = request.getParameter("pageno");
        Page<User> page = userService.pageQueryUser(pageno);

        // 逻辑查询
//        // 获取session
//        HttpSession session = request.getSession();
//
//        // 从session中取全部信息的List集合
//        List<User> userAllList = (List<User>) session.getAttribute("userAllList");
//
//        // 如果返回的List集合为null，则连接数据库
//        // 执行查询语句返回结果集
//        // 遍历结果集，封装用户对象
//        // 将用户对象存储到全部信息的List集合中去
//        if (userAllList == null) {
//            userAllList = UserDaoImpl.queryAll();
//
//            // 将List集合存储到session对象中
//            session.setAttribute("userAllList", userAllList);
//        }
//
//        // 根据页码从全部信息的List集合中取出小的List集合
//        List<User> userList = new ArrayList<>();
//        int beginIndex = Const.PAGE_SIZE * (pageNo - 1);
//        int endIndex = Math.min(pageNo * Const.PAGE_SIZE, userAllList.size());
//        for (int i = beginIndex; i < endIndex; i++) {
//            userList.add(userAllList.get(i));
//        }
        // 物理查询

        // 将分页查询的其余相关信息放入request中
        request.setAttribute("page", page);
        // 转发
        request.getRequestDispatcher("/system/user.jsp").forward(request, response);
    }
}
