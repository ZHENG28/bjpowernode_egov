package com.zj.egov.system.service;

import com.zj.egov.bean.Page;
import com.zj.egov.bean.User;

public interface UserService
{
    /**
     * 分页查询用户信息
     *
     * @param pageno 当前页码
     * @return 分页查询的结果页
     */
    Page<User> pageQueryUser(String pageno);

    /**
     * 新增用户信息
     *
     * @param user 接收到的用户信息
     * @return 是否成功存入数据库：-1-用户代码已存在; 1-成功存入
     */
    int insertUser(User user);

    /**
     * 检查用户代码是否存在
     *
     * @param usercode 用户代码
     * @return true-存在; false-不存在
     */
    boolean checkUserCode(String usercode);

    /**
     * 批量删除用户
     *
     * @param usercodes 即将要删除的用户代码
     * @return 是否全部删除成功
     */
    boolean deleteUsers(String[] usercodes);

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
     * @return 是否修改成功
     */
    boolean updateUser(User user);
}
