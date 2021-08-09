package com.zj.egov.fe.service;

import com.zj.egov.bean.EnInv;
import com.zj.egov.bean.Enterprise;
import com.zj.egov.bean.Page;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;
import java.util.Map;

public interface EnterpriseService
{
    /**
     * 查询企业信息
     *
     * @param orgcode 企业编号
     * @return 企业信息
     */
    Enterprise findByCode(String orgcode);

    /**
     * 保存企业信息
     *
     * @param en        企业信息
     * @param enInvList 投资者列表
     * @return 是否新增成功
     */
    boolean save(Enterprise en, List<EnInv> enInvList);

    /**
     * 分页查询企业信息
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
    DefaultPieDataset getDataset(String orgcode);
}
