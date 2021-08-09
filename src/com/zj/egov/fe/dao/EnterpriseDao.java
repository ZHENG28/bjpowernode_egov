package com.zj.egov.fe.dao;

import com.zj.egov.bean.EnInv;
import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.Page;

import java.util.List;
import java.util.Map;

public interface EnterpriseDao
{
    /**
     * 查询企业信息
     *
     * @param orgcode 企业编号
     * @return 企业信息
     */
    Enterprise selectByCode(String orgcode);

    /**
     * 新增企业信息
     *
     * @param en        企业信息
     * @param enInvList 投资者列表
     * @return 1+投资者个数-新增成功; 0-新增失败
     */
    int insert(Enterprise en, List<EnInv> enInvList);

    /**
     * 动态分页查询企业信息
     *
     * @param conditionMap 动态查询条件
     * @return 当前页
     */
    Page<Enterprise> pageQueryEn(Map<String, String> conditionMap);

    /**
     * 获取投资人比例数据集合
     *
     * @param orgcode 组织代码
     * @return 图表数据
     */
    Map<String, Number> makeChart(String orgcode);
}
