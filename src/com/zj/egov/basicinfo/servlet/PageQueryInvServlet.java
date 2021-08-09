package com.zj.egov.basicinfo.servlet;

import com.zj.egov.basicinfo.service.BasicInfoService;
import com.zj.egov.basicinfo.service.impl.BasicInfoServiceImpl;
import com.zj.egov.bean.Invest;
import com.zj.egov.bean.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询投资人
 */
public class PageQueryInvServlet extends HttpServlet
{
    private BasicInfoService basicInfoService = new BasicInfoServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 动态查询需获取参数
        String pageno = request.getParameter("pageno");
        String invregnum = request.getParameter("invregnum");
        String invname = request.getParameter("invname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");

        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("pageno", pageno);
        conditionMap.put("invregnum", invregnum);
        conditionMap.put("invname", invname);
        conditionMap.put("startdate", startdate);
        conditionMap.put("enddate", enddate);

        Page<Invest> page = basicInfoService.pageQueryInv(conditionMap);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getParameter("forward")).forward(request, response);
    }
}
