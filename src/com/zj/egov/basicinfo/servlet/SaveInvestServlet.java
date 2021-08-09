package com.zj.egov.basicinfo.servlet;

import com.zj.egov.basicinfo.service.BasicInfoService;
import com.zj.egov.basicinfo.service.impl.BasicInfoServiceImpl;
import com.zj.egov.bean.Invest;
import com.zj.egov.bean.User;
import com.zj.egov.basicinfo.dao.impl.BasicInfoDaoImpl;
import com.zj.egov.util.Const;
import com.zj.egov.util.DateUtil;
import com.zj.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 保存投资人
 */
public class SaveInvestServlet extends HttpServlet
{
    private BasicInfoService basicInfoService = new BasicInfoServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Invest inv = new Invest();
        WebUtil.makeRequestToObject(request, inv);
        User user = (User) request.getSession(false).getAttribute("user");
        String regdate = DateUtil.format(new Date(), Const.DATE_FORMAT_YMD);
        inv.setRegdate(regdate);
        inv.setUsercode(user.getUsercode());

        if (basicInfoService.insertInv(inv)) {
            response.sendRedirect("/EGOV/basicinfo/exoticOrgList.jsp");
        } else {
            System.out.println("新增投资人信息失败");
        }
    }
}
