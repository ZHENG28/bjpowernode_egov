package com.zj.egov.fe.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zj.egov.fe.dao.EnterpriseDao;
import com.zj.egov.fe.dao.impl.EnterpriseDaoImpl;
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
 * 检查机构代码
 */
public class CheckOrgcodeServlet extends HttpServlet
{
    private EnterpriseService enService = new EnterpriseServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orgcode = request.getParameter("orgcode");

        Map<String, Object> jsonMap = new HashMap<>();
        if (enService.findByCode(orgcode) != null) {
            jsonMap.put("msg", "该组织机构代码已经存在");
            jsonMap.put("ok", false);
        } else {
            jsonMap.put("msg", "该组织机构代码可以使用");
            jsonMap.put("ok", true);
        }
        String json = JSONObject.toJSONString(jsonMap);
        response.getWriter().println(json);
    }
}
