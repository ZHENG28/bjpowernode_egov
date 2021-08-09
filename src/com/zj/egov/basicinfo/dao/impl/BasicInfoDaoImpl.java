package com.zj.egov.basicinfo.dao.impl;

import com.zj.egov.basicinfo.dao.BasicInfoDao;
import com.zj.egov.bean.Invest;
import com.zj.egov.bean.Page;
import com.zj.egov.util.JDBCUtil;
import com.zj.egov.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicInfoDaoImpl implements BasicInfoDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    @Override
    public Page<Invest> pageQueryInv(Map<String, String> conditionMap)
    {
        Page<Invest> page = new Page<>(conditionMap.get("pageno"));
        StringBuffer pageQuerySQL = new StringBuffer("select inv.invregnum,inv.invname,inv.regdate,inv.cty,tu.username from t_invest inv join t_user tu on inv.usercode=tu.usercode where 1=1");
        StringBuffer totalSizeSQL = new StringBuffer("select count(*) as total from t_invest inv join t_user tu on inv.usercode=tu.usercode where 1=1");
        List<String> paramList = new ArrayList<>();
        if (StringUtil.isNotEmpty(conditionMap.get("invregnum"))) {
            pageQuerySQL.append(" and inv.invregnum = ?");
            totalSizeSQL.append(" and inv.invregnum = ?");
            paramList.add(conditionMap.get("invregnum"));
        }
        if (StringUtil.isNotEmpty(conditionMap.get("invname"))) {
            pageQuerySQL.append(" and inv.invname like ?");
            totalSizeSQL.append(" and inv.invname like ?");
            paramList.add("%" + conditionMap.get("invname") + "%");
        }
        if (StringUtil.isNotEmpty(conditionMap.get("startdate"))) {
            pageQuerySQL.append(" and inv.regdate >= ?");
            totalSizeSQL.append(" and inv.regdate >= ?");
            paramList.add(conditionMap.get("startdate"));
        }
        if (StringUtil.isNotEmpty(conditionMap.get("enddate"))) {
            pageQuerySQL.append(" and inv.regdate <= ?");
            totalSizeSQL.append(" and inv.regdate <= ?");
            paramList.add(conditionMap.get("enddate"));
        }
        String querySql = page.getSql(pageQuerySQL.toString(), "inv.regdate");

        try {
            conn = JDBCUtil.getConnection();
            // 分页查询
            ps = conn.prepareStatement(querySql);
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i + 1, paramList.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Invest inv = new Invest();
                inv.setInvregnum(rs.getString("invregnum"));
                inv.setInvname(rs.getString("invname"));
                inv.setRegdate(rs.getString("regdate"));
                inv.setCty(rs.getString("cty"));
                inv.setUsername(rs.getString("username"));
                page.getDataList().add(inv);
            }

            // 查询总记录条数
            ps = conn.prepareStatement(totalSizeSQL.toString());
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i + 1, paramList.get(i));
            }
            rs = ps.executeQuery();
            page.setTotalsize(rs.next() ? rs.getInt("total") : 0);
            page.setPageno(page.getTotalsize() == 0 ? 0 : page.getPageno());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：分页动态查询投资人信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return page;
    }

    @Override
    public int insertInv(Invest inv)
    {
        String sql = "insert into t_invest values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, inv.getInvregnum());
            ps.setString(2, inv.getInvname());
            ps.setString(3, inv.getCty());
            ps.setString(4, inv.getOrgcode());
            ps.setString(5, inv.getContactman());
            ps.setString(6, inv.getContacttel());
            ps.setString(7, inv.getEmail());
            ps.setString(8, inv.getRemark());
            ps.setString(9, inv.getUsercode());
            ps.setString(10, inv.getRegdate());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：新增投资人信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return result;
    }

    public Invest selectByNum(String invregnum)
    {
        Invest inv = null;
        String sql = "select * from t_invest where invregnum = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, invregnum);
            rs = ps.executeQuery();
            if (rs.next()) {
                inv = new Invest();
                inv.setInvname(rs.getString("invname"));
                inv.setCty(rs.getString("cty"));
                inv.setOrgcode(rs.getString("orgcode"));
                inv.setContactman(rs.getString("contactman"));
                inv.setContacttel(rs.getString("contacttel"));
                inv.setEmail(rs.getString("email"));
                inv.setRemark(rs.getString("remark"));
                inv.setUsercode(rs.getString("usercode"));
                inv.setRegdate(rs.getString("regdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：查找投资人信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return inv;
    }

    public static int findLastInvId()
    {
        String sql = "select max(invregnum) as lastId from t_invest";
        int lastId = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            lastId = rs.next() ? rs.getInt("lastId") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return lastId;
    }
}
