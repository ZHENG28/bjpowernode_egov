package com.zj.egov.auth.dao;

import com.zj.egov.bean.Auth;

import java.util.Map;

public interface AuthDao
{
    /**
     * 新增核准件信息
     *
     * @param authMap 核准件信息
     * @return 1-新增成功; 0-新增失败
     */
    int insertAuth(Map<String, String> authMap);

    /**
     * 查找核准件
     *
     * @param authno 核准件编号
     * @return 核准件信息
     */
    Auth selectByAuthno(String authno);

    /**
     * 反馈核准件
     *
     * @param authno 核准件编号
     * @return 1-更新成功; 0-更新失败
     */
    int update(String authno);
}
