package com.zj.egov.util;

import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtil
{
    public static final String URL = "jdbc:mysql://localhost:3306/bjpowernode";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "MySQL123!";
    // 放在ThreadLocal中的都是线程安全的
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private JDBCUtil() {}

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs)
    {
        if (conn != null) {
            try {
                conn.close();
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 开启事务和提交事务需要上抛异常，以便于调用时catch捕获
    // 回滚事务和结束事务则不需要上抛，直接catch即可
    // 关闭自动提交机制，即开启事务手动提交
    public static void beginTransaction(Connection conn) throws SQLException
    {
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }

    public static void commitTransaction(Connection conn) throws SQLException
    {
        if (conn != null) {
            conn.commit();
        }
    }

    public static void rollbackTransaction(Connection conn)
    {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void endTransaction(Connection conn)
    {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
