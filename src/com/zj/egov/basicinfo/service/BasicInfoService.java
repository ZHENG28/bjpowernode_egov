package com.zj.egov.basicinfo.service;

import com.zj.egov.bean.Invest;
import com.zj.egov.bean.Page;

import java.util.Map;

public interface BasicInfoService
{
    /**
     * 分页动态查询投资人信息
     *
     * @param conditionMap 条件集合
     * @return 当前页
     */
    Page<Invest> pageQueryInv(Map<String, String> conditionMap);

    /**
     * 新增投资人信息
     *
     * @param inv 投资人信息
     * @return 是否增加成功
     */
    boolean insertInv(Invest inv);

    /**
     * 查找投资人信息
     *
     * @param invregnum 投资人注册代码
     * @return 投资人信息
     */
    Invest selectByNum(String invregnum);
}
