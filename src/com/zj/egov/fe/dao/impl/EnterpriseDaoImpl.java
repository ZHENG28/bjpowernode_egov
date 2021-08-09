package com.zj.egov.fe.dao.impl;

import com.zj.egov.bean.EnInv;
import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.Page;
import com.zj.egov.fe.dao.EnterpriseDao;
import com.zj.egov.util.JDBCUtil;
import com.zj.egov.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterpriseDaoImpl implements EnterpriseDao
{
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    @Override
    public Enterprise selectByCode(String orgcode)
    {
        String sql = "select * from t_enterprise where orgcode = ?";
        Enterprise en = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, orgcode);
            rs = ps.executeQuery();
            if (rs.next()) {
                en = new Enterprise();
                en.setOrgcode(orgcode);
                en.setCnname(rs.getString("cnname"));
                en.setRegcap(rs.getString("regcap"));
                en.setRegcry(rs.getString("regcry"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：查询企业信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return en;
    }

    @Override
    public int insert(Enterprise en, List<EnInv> enInvList)
    {
        String sql = "insert into t_enterprise values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, en.getOrgcode());
            ps.setString(2, en.getRegno());
            ps.setString(3, en.getCnname());
            ps.setString(4, en.getEnname());
            ps.setString(5, en.getContactman());
            ps.setString(6, en.getContacttel());
            ps.setString(7, en.getRegcap());
            ps.setString(8, en.getOutregcap());
            ps.setString(9, en.getRegcry());
            ps.setString(10, en.getUsercode());
            ps.setString(11, en.getRegdate());
            result = ps.executeUpdate();

            sql = "insert into t_en_inv values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            for (EnInv enInv : enInvList) {
                ps.setString(1, en.getOrgcode());
                ps.setString(2, enInv.getInvregnum());
                ps.setInt(3, Integer.parseInt(enInv.getRegcap()));
                ps.setInt(4, Integer.parseInt(enInv.getScale()));
                ps.addBatch();
            }
            int[] count = ps.executeBatch();
            for (int i : count) {
                result += i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：新增企业信息异常");
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return result;
    }

    @Override
    public Page<Enterprise> pageQueryEn(Map<String, String> conditionMap)
    {
        Page<Enterprise> page = new Page<>(conditionMap.get("pageno"));
        StringBuffer pageQuerySQL = new StringBuffer("select e.orgcode, e.cnname, e.regdate, u.username from t_enterprise e join t_user u on u.usercode = e.usercode where 1=1");
        StringBuffer totalSizeSQL = new StringBuffer("select count(*) as total from t_enterprise e join t_user u on u.usercode = e.usercode where 1=1");
        List<String> paramList = new ArrayList<>();
        if (StringUtil.isNotEmpty(conditionMap.get("orgcode"))) {
            pageQuerySQL.append(" and e.orgcode = ?");
            totalSizeSQL.append(" and e.orgcode = ?");
            paramList.add(conditionMap.get("orgcode"));
        }
        if (StringUtil.isNotEmpty(conditionMap.get("cnname"))) {
            pageQuerySQL.append(" and e.cnname like ?");
            totalSizeSQL.append(" and e.cnname like ?");
            paramList.add(conditionMap.get("cnname"));
        }
        if (StringUtil.isNotEmpty(conditionMap.get("startdate"))) {
            pageQuerySQL.append(" and e.regdate >= ?");
            totalSizeSQL.append(" and e.regdate >= ?");
            paramList.add(conditionMap.get("startdate"));
        }
        if (StringUtil.isNotEmpty(conditionMap.get("enddate"))) {
            pageQuerySQL.append(" and e.regdate <= ?");
            totalSizeSQL.append(" and e.regdate <= ?");
            paramList.add(conditionMap.get("enddate"));
        }
        String querySql = page.getSql(pageQuerySQL.toString(), "e.regdate");

        try {
            conn = JDBCUtil.getConnection();
            // 分页查询
            ps = conn.prepareStatement(querySql);
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i + 1, paramList.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Enterprise en = new Enterprise();
                en.setOrgcode(rs.getString("orgcode"));
                en.setCnname(rs.getString("cnname"));
                en.setRegdate(rs.getString("regdate"));
                en.setUsername(rs.getString("username"));
                page.getDataList().add(en);
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
            throw new RuntimeException("DAO：动态分页查询企业信息异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
        return page;
    }

    @Override
    public Map<String, Number> makeChart(String orgcode)
    {
        String sql = "select i.invname, ei.regcap from t_en_inv ei join t_invest i on ei.invregnum = i.invregnum where ei.orgcode=?";
//        StringBuffer chartXml = new StringBuffer();
//        chartXml.append("<?xml version='1.0' encoding='GB18030'?>");
//        chartXml.append("<graph showNames='1'  decimalPrecision='0'>");
        Map<String, Number> chartdataMap = new HashMap<>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, orgcode);
            rs = ps.executeQuery();
            while (rs.next()) {
                String invname = rs.getString("invname");
                int regcap = Integer.parseInt(rs.getString("regcap"));
                chartdataMap.put(invname, regcap);
//                chartXml.append("<set name='" + invname + "' value='" + regcap + "'/>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO：图表数据生成异常");
        } finally {
            JDBCUtil.close(null, ps, rs);
        }
//        chartXml.append("</graph>");
//        return chartXml.toString();
        return chartdataMap;
    }
}
