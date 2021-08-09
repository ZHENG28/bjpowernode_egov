package com.zj.egov.auth.service.impl;

import com.zj.egov.auth.dao.AuthDao;
import com.zj.egov.auth.dao.impl.AuthDaoImpl;
import com.zj.egov.auth.service.AuthService;
import com.zj.egov.bean.Auth;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthServiceImpl implements AuthService
{
    private AuthDao authDao = new AuthDaoImpl();
    static Connection conn = null;

    @Override
    public boolean saveAuth(Map<String, String> authMap)
    {
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            count = authDao.insertAuth(authMap);
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
    public Auth findByAuthno(String authno)
    {
        Auth auth = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            auth = authDao.selectByAuthno(authno);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return auth;
    }

    @Override
    public boolean feedback(String authno)
    {
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            count = authDao.update(authno);
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
}
