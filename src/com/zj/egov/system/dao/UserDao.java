package com.zj.egov.system.dao;

import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;

import java.util.List;

public interface UserDao
{
    /**
     * 查询总记录条数
     *
     * @return
     */
    int selectTotalsize();

    /**
     * 分页查询用户信息
     *
     * @param pageno 当前页码
     * @return 分页查询的结果页
     */
    Page<User> pageQueryUser(String pageno);

    /**
     * 根据用户代码查询用户信息
     *
     * @param usercode 用户代码
     * @return null-用户不存在
     */
    User selectByUsercode(String usercode);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 1-保存成功; 0-保存失败
     */
    int insert(User user);

    /**
     * 批量删除用户
     *
     * @param usercodes 即将要删除的用户代码
     * @return 共执行删除操作执行了count次
     */
    int deleteUsers(String[] usercodes);

    /**
     * 查找要修改的用户
     *
     * @param usercode 用户代码
     * @return 要修改的用户信息
     */
    User selectUser(String usercode);

    /**
     * 修改用户信息
     *
     * @param user 修改后的用户信息
     * @return 1-修改成功; 0-修改失败
     */
    int updateUser(User user);
}
