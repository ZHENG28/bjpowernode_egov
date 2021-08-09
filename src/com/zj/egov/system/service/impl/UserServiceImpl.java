package com.zj.egov.system.service.impl;

import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;
import com.zj.egov.system.dao.UserDao;
import com.zj.egov.system.dao.impl.UserDaoImpl;
import com.zj.egov.system.service.UserService;
import com.zj.egov.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService
{
    private UserDao userDao = new UserDaoImpl();
    static Connection conn = null;

    @Override
    public Page<User> pageQueryUser(String pageno)
    {
        Page page = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            page = userDao.pageQueryUser(pageno);
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
    public int insertUser(User user)
    {
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);

            // 先查找是否存在
            User u = userDao.selectByUsercode(user.getUsercode());
            if (u != null) {
                return -1;
            }
            // 如果不存在则插入数据库
            count = userDao.insert(user);
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return count;
    }

    @Override
    public boolean checkUserCode(String usercode)
    {
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            User u = userDao.selectByUsercode(usercode);
            if (u != null) {
                return true;
            }
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return false;
    }

    @Override
    public boolean deleteUsers(String[] usercodes)
    {
        boolean ok = true;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            ok = userDao.deleteUsers(usercodes) == usercodes.length;
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            ok = false;
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return ok;
    }

    @Override
    public User selectUser(String usercode)
    {
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            user = userDao.selectUser(usercode);
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

    @Override
    public boolean updateUser(User user)
    {
        boolean ok = true;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.beginTransaction(conn);
            ok = userDao.updateUser(user) == 1;
            JDBCUtil.commitTransaction(conn);
        } catch (SQLException e) {
            ok = false;
            JDBCUtil.rollbackTransaction(conn);
            e.printStackTrace();
        } finally {
            JDBCUtil.endTransaction(conn);
            JDBCUtil.close(conn, null, null);
        }
        return ok;
    }
}
