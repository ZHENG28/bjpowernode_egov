package com.zj.egov.user.service;

import com.zj.egov.bean.User;

public interface LogService
{
    /**
     * 登录系统
     * @param orgtype 组织机构
     * @param usercode 用户代码
     * @param userpwd 用户密码
     * @return 用户信息
     */
    User login(String orgtype, String usercode, String userpwd);
}
