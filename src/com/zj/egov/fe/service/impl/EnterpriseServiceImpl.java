package com.zj.egov.fe.service.impl;

import com.zj.egov.bean.EnInv;
import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.Page;
import com.zj.egov.fe.dao.EnterpriseDao;
import com.zj.egov.fe.dao.impl.EnterpriseDaoImpl;
import com.zj.egov.fe.service.EnterpriseService;
import com.zj.egov.util.JDBCUtil;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EnterpriseServiceImpl implements EnterpriseService
{
    private EnterpriseDao enterpriseDao = new EnterpriseDaoImpl();
    static Connection conn = null;

    @Override
    public Enterprise findByCode(String orgcode)
    {
        Enterprise en = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            en = enterpriseDao.selectByCode(orgcode);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return en;
    }

    @Override
    public boolean save(Enterprise en, List<EnInv> enInvList)
    {
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            count = enterpriseDao.insert(en, enInvList);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return count == 1 + enInvList.size();
    }

    @Override
    public Page<Enterprise> pageQueryEn(Map<String, String> conditionMap)
    {
        Page<Enterprise> page = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            page = enterpriseDao.pageQueryEn(conditionMap);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return page;
    }

    @Override
    public DefaultPieDataset getDataset(String orgcode)
    {
//        String chartXml = null;
        Map<String, Number> chartdataMap = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
//            chartXml = enterpriseDao.makeChart(orgcode);
            chartdataMap = enterpriseDao.makeChart(orgcode);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String s : chartdataMap.keySet()) {
            dataset.setValue(s, chartdataMap.get(s));
        }
        return dataset;
    }
}
