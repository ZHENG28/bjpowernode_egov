package com.zj.egov.basicinfo.servlet;

import com.zj.egov.basicinfo.service.BasicInfoService;
import com.zj.egov.basicinfo.service.impl.BasicInfoServiceImpl;
import com.zj.egov.bean.Invest;
import com.zj.egov.basicinfo.dao.impl.BasicInfoDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查看投资人
 */
public class ViewInvServlet extends HttpServlet
{
    private BasicInfoService basicInfoService = new BasicInfoServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String invregnum = request.getParameter("invregnum");
        Invest inv = basicInfoService.selectByNum(invregnum);
        if (inv != null) {
            request.setAttribute("inv", inv);
            request.getRequestDispatcher("/basicinfo/exoticOrgView.jsp").forward(request, response);
        }else{
            System.out.println("查找投资人信息失败");
        }
    }
}
