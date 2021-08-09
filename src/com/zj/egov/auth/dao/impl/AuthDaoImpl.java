package com.zj.egov.auth.dao.impl;

import com.zj.egov.auth.dao.AuthDao;
import com.zj.egov.bean.Auth;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthDaoImpl implements AuthDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    @Override
    public int insertAuth(Map<String, String> authMap)
    {
        String sql = "insert into t_auth values(?, ?, ?, ?, ?, ?, null, ?, '0')";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, authMap.get("orgcode"));
            ps.setString(2, authMap.get("remark"));
            ps.setString(3, authMap.get("contactman"));
            ps.setString(4, authMap.get("contacttel"));
            ps.setString(5, authMap.get("filename"));
            ps.setString(6, authMap.get("fileremark"));
            ps.setString(7, authMap.get("usercode"));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：新增核准件信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return result;
    }

    @Override
    public Auth selectByAuthno(String authno)
    {
        Auth auth = null;
        String sql = "select ta.contactman, ta.contacttel, te.regdate, te.regcap, te.regcry from t_auth ta join t_enterprise te on ta.orgcode = te.orgcode where ta.authno = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, authno);
            rs = ps.executeQuery();
            if (rs.next()) {
                auth = new Auth();
                auth.setAuthno(authno);
                auth.setRegdate(rs.getString("regdate"));
                auth.setContactman(rs.getString("contactman"));
                auth.setContacttel(rs.getString("contacttel"));
                auth.setRegcap(rs.getString("regcap"));
                auth.setRegcry(rs.getString("regcry"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：查找核准件异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return auth;
    }

    @Override
    public int update(String authno)
    {
        int result = 0;
        String sql = "update t_auth set feedback = '1' where authno = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, authno);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：反馈核准件异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return result;
    }
}
