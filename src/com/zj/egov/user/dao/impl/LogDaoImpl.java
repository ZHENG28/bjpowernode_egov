package com.zj.egov.user.dao.impl;

import com.zj.egov.bean.User;
import com.zj.egov.user.dao.LogDao;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDaoImpl implements LogDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    @Override
    public User login(String orgtype, String usercode, String userpwd)
    {
        User user = null;
        String sql = "select * from t_user where orgtype = ? and usercode = ? and userpswd = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, orgtype);
            ps.setString(2, usercode);
            ps.setString(3, userpwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String regdate = rs.getString("regdate");
                user = new User(usercode, username, userpwd, orgtype, regdate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：登录异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return user;
    }
}
