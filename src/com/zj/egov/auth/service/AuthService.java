package com.zj.egov.auth.service;

import com.zj.egov.bean.Auth;

import java.util.Map;

public interface AuthService
{
    /**
     * 保存核准件信息
     *
     * @param authMap 核准件信息
     * @return 是否保存成功
     */
    boolean saveAuth(Map<String, String> authMap);

    /**
     * 查找核准件
     *
     * @param authno 核准件编号
     * @return 核准件信息
     */
    Auth findByAuthno(String authno);

    /**
     * 反馈核准件
     *
     * @param authno 核准件编号
     * @return 是否反馈成功
     */
    boolean feedback(String authno);
}
