package com.bj.dfmanager.service;

import com.bj.dfmanager.entity.User;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.user.UserSearchVO;
import com.bj.dfmanager.vo.user.UserVO;

public interface UserService {

    /**
     * 用户登录
     */
    Result login(UserVO userVO);

    /**
     * 根据用户ID查询用户
     */
    User findById(Integer id);

    /**
     * 判断用户名是否存在
     */
    Result userNameIsExist(String userName);

    /**
     * 添加/修改用户
     */
    Result addOrUpdate(UserVO userVO);

    /**
     * 查询用户列表
     */
    Result queryList(UserSearchVO userSearchVO);

    /**
     * 查询用户信息
     */
    Result queryById(Integer userId);

    /**
     * 删除用户信息
     */
    Result delete(Integer userId);

    /**
     * 启用/停用用户
     */
    Result startOrStop(UserVO userVO);

    /**
     * 查询用户已拥有的角色
     */
    Result userHaveRole(Integer userId);

    /**
     * 查询用户未拥有的角色
     */
    Result userNotHaveRole(Integer userId);

    /**
     * 用户绑定角色
     */
    Result userBindRole(UserVO userVO);

    /**
     * 查询用户列表
     */
    Result userList();

}