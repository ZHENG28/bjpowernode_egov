package com.zj.egov.fe.servlet;

import com.zj.egov.fe.service.EnterpriseService;
import com.zj.egov.fe.service.impl.EnterpriseServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ChartsXMLServlet extends HttpServlet
{
    private EnterpriseService enService = new EnterpriseServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String orgcode = request.getParameter("orgcode");
        DefaultPieDataset dataset = enService.getDataset(orgcode);
        JFreeChart chart = ChartFactory.createPieChart3D(null, dataset, true, false, false);
        response.setContentType("image/jpeg;charset=utf-8");
        File file = new File(getServletContext().getRealPath(".") + "/charts/" + orgcode + ".jpg");
        ChartUtilities.saveChartAsJPEG(file, chart, 400, 300);
    }
}
