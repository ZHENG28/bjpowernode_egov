package com.zj.egov.basicinfo.dao;

import com.zj.egov.bean.Invest;
import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;

import java.util.Map;

public interface BasicInfoDao
{
    /**
     * 分页动态查询投资人信息
     *
     * @param cond 动态查询条件
     * @return 当前页信息
     */
    Page<Invest> pageQueryInv(Map<String, String> cond);

    /**
     * 新增投资人信息
     *
     * @param inv 投资人信息
     * @return 1-插入成功; 0-插入失败
     */
    int insertInv(Invest inv);

    /**
     * 查找投资人信息
     *
     * @param invregnum 投资人代码
     * @return 投资人信息
     */
    Invest selectByNum(String invregnum);
}