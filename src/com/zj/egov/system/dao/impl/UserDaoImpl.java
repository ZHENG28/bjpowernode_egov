package com.zj.egov.system.dao.impl;

import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;
import com.zj.egov.system.dao.UserDao;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    @Override
    public int selectTotalsize()
    {
        String sql = "select count(*) as total from t_user";
        int total = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：查询总记录条数异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return total;
    }

    @Override
    public Page<User> pageQueryUser(String pageno)
    {
        Page<User> page = new Page<User>(pageno);
        String sql = page.getSql("select * from t_user", "regdate");
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsercode(rs.getString("usercode"));
                user.setUsername(rs.getString("username"));
                user.setOrgtype(rs.getString("orgtype"));
                page.getDataList().add(user);
            }
            page.setTotalsize(selectTotalsize());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：分页查询用户信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return page;
    }

    @Override
    public User selectByUsercode(String usercode)
    {
        String sql = "select * from t_user where usercode=?";
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usercode);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setOrgtype(rs.getString("orgtype"));
                user.setUserpswd(rs.getString("userpswd"));
                user.setRegdate(rs.getString("regdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：根据用户代码查询用户信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return user;
    }

    @Override
    public int insert(User user)
    {
        String sql = "insert into t_user values(?, ?, ?, ?, ?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsercode());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getOrgtype());
            ps.setString(4, user.getUserpswd());
            ps.setString(5, user.getRegdate());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：保存用户信息异常");
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return result;
    }

    @Override
    public int deleteUsers(String[] userCodes)
    {
        int count = 0;
        String sql = "delete from t_user where usercode=?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (String userCode : userCodes) {
                ps.setString(1, userCode);
//                result += ps.executeUpdate();
                ps.addBatch();
            }
            int[] arr = ps.executeBatch();
            for (int i : arr) {
                count += i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：批量删除用户异常");
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return count;
    }

    @Override
    public User selectUser(String usercode)
    {
        String sql = "select * from t_user where usercode=?";
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usercode);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsercode(usercode);
                user.setUsername(rs.getString("username"));
                user.setOrgtype(rs.getString("orgtype"));
                user.setUserpswd(rs.getString("userpswd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：查找要修改的用户异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return user;
    }

    @Override
    public int updateUser(User user)
    {
        String sql = "update t_user set username=?, userpswd=?, orgtype=? where usercode=?";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUserpswd());
            ps.setString(3, user.getOrgtype());
            ps.setString(4, user.getUsercode());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：修改用户信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return result;
    }

    // 逻辑查询
//    public static List<User> queryAll()
//    {
//        List<User> userList = new ArrayList<>();
//        String sql = "select * from t_user order by regdate desc";
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User user = new User();
//                user.setUsercode(rs.getString("usercode"));
//                user.setUsername(rs.getString("username"));
//                user.setOrgtype(rs.getString("orgtype"));
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, rs);
//        }
//        return userList;
//    }

    // 物理查询
//    public static List<User> queryAll(int begin, int length)
//    {
//        String sql = "select * from t_user order by regdate desc limit ?, ?";
//        List<User> userList = new ArrayList<>();
//        try {
//            conn = JDBCUtil.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, begin);
//            ps.setInt(2, length);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User user = new User();
//                user.setUsercode(rs.getString("usercode"));
//                user.setUsername(rs.getString("username"));
//                user.setOrgtype(rs.getString("orgtype"));
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(conn, ps, rs);
//        }
//        return userList;
//    }
}
