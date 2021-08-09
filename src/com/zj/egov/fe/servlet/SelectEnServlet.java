package com.zj.egov.fe.servlet;

import com.zj.egov.bean.Enterprise;
import com.zj.egov.fe.service.EnterpriseService;
import com.zj.egov.fe.service.impl.EnterpriseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 选择企业
 */
public class SelectEnServlet extends HttpServlet
{
    private EnterpriseService enService = new EnterpriseServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orgcode = request.getParameter("orgcode");
        Enterprise en = enService.findByCode(orgcode);
        request.setAttribute("en", en);
        request.getRequestDispatcher("/auth/openAccountAuthDetail.jsp").forward(request, response);
    }
}
