package com.zj.egov.basicinfo.service.impl;

import com.zj.egov.basicinfo.dao.BasicInfoDao;
import com.zj.egov.basicinfo.dao.impl.BasicInfoDaoImpl;
import com.zj.egov.basicinfo.service.BasicInfoService;
import com.zj.egov.bean.Invest;
import com.zj.egov.bean.Page;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class BasicInfoServiceImpl implements BasicInfoService
{
    private BasicInfoDao basicInfoDao = new BasicInfoDaoImpl();
    static Connection conn = null;

    @Override
    public Page<Invest> pageQueryInv(Map<String, String> conditionMap)
    {
        Page<Invest> page = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            page = basicInfoDao.pageQueryInv(conditionMap);
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
    public boolean insertInv(Invest inv)
    {
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            count = basicInfoDao.insertInv(inv);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return count == 1;
    }

    @Override
    public Invest selectByNum(String invregnum)
    {
        Invest inv = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            inv = basicInfoDao.selectByNum(invregnum);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return inv;
    }
}
