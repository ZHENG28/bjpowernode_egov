package com.zj.egov.fe.servlet;

import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.Page;
import com.zj.egov.fe.service.EnterpriseService;
import com.zj.egov.fe.service.impl.EnterpriseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询企业
 */
public class PageQueryEnServlet extends HttpServlet
{
    private EnterpriseService enService = new EnterpriseServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 接收数据
        String orgcode = request.getParameter("orgcode");
        String cnname = request.getParameter("cnname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String pageno = request.getParameter("pageno");

        // 封装DTO对象
        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("orgcode", orgcode);
        conditionMap.put("cnname", cnname);
        conditionMap.put("startdate", startdate);
        conditionMap.put("enddate", enddate);
        conditionMap.put("pageno", pageno);

        // 调用Model
        Page<Enterprise> page = enService.pageQueryEn(conditionMap);
        request.setAttribute("page", page);

        // 调用View
        request.getRequestDispatcher("/auth/orgcodeSelect.jsp").forward(request, response);
    }
}
