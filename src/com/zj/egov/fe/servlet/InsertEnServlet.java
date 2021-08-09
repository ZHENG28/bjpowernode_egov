package com.zj.egov.fe.servlet;

import com.zj.egov.bean.EnInv;
import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.User;
import com.zj.egov.fe.dao.impl.EnterpriseDaoImpl;
import com.zj.egov.fe.service.EnterpriseService;
import com.zj.egov.fe.service.impl.EnterpriseServiceImpl;
import com.zj.egov.util.Const;
import com.zj.egov.util.DateUtil;
import com.zj.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新增企业
 */
public class InsertEnServlet extends HttpServlet
{
    private EnterpriseService enterpriseService = new EnterpriseServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 数据封装
        Enterprise en = new Enterprise();
        WebUtil.makeRequestToObject(request, en);
        en.setUsercode(((User) request.getSession(false).getAttribute("user")).getUsercode());
        en.setRegdate(DateUtil.format(new Date(), Const.DATE_FORMAT_YMD));

        String[] invregnums = request.getParameterValues("invregnum");
        String[] regcapItems = request.getParameterValues("regcapItem");
        String[] scales = request.getParameterValues("scale");
        List<EnInv> enInvList = new ArrayList<>();
        for (int i = 0; i < invregnums.length; i++) {
            EnInv enInv = new EnInv();
            enInv.setInvregnum(invregnums[i]);
            enInv.setRegcap(regcapItems[i]);
            enInv.setScale(scales[i]);
            enInvList.add(enInv);
        }

        // 调用Model
        boolean saveSuccess = enterpriseService.save(en, enInvList);

        // 调用View
        if (saveSuccess) {
            response.sendRedirect("/EGOV/foreignExchange/newInputOrg.jsp");
        } else {
            System.out.println("保存企业信息失败");
        }
    }
}
