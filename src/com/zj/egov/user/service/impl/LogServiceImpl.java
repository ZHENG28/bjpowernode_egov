package com.zj.egov.user.service.impl;

import com.zj.egov.bean.User;
import com.zj.egov.user.dao.LogDao;
import com.zj.egov.user.dao.impl.LogDaoImpl;
import com.zj.egov.user.service.LogService;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class LogServiceImpl implements LogService
{
    static Connection conn = null;
    private LogDao logDao = new LogDaoImpl();

    @Override
    public User login(String orgtype, String usercode, String userpwd)
    {
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            user = logDao.login(orgtype, usercode, userpwd);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return user;
    }
}
