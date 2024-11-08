package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoSearchVO;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoVO;

public interface UserSystemInfoService {

    /**
     * 查询用户系统信息列表
     */
    Result queryList(UserSystemInfoSearchVO userSystemInfoSearchVO);

    /**
     * 判断系统编码是否存在
     */
    Result systemCodeIsExist(String systemCode);

    /**
     * 添加/修改用户系统信息
     */
    Result addOrUpdate(UserSystemInfoVO userSystemInfoVO);

    /**
     * 查询用户系统信息
     */
    Result queryById(Integer id);

    /**
     * 删除用户系统信息
     */
    Result delete(Integer id);

    /**
     * 启用/停用用户系统信息
     */
    Result startOrStop(UserSystemInfoVO userSystemInfoVO);

    /**
     * 查询用户系统已拥有的服务
     */
    Result userSystemHaveService(Integer id);

    /**
     * 查询用户系统未拥有的服务
     */
    Result userSystemNotHaveService(Integer id);

    /**
     * 用户系统绑定服务
     */
    Result userSystemBindService(UserSystemInfoVO userSystemInfoVO);

    /**
     * 查询用户系统已拥有的指标
     */
    Result userSystemHaveTarget(Integer id);

    /**
     * 查询用户系统未拥有的指标
     */
    Result userSystemNotHaveTarget(Integer id);

    /**
     * 用户系统绑定指标
     */
    Result userSystemBindTarget(UserSystemInfoVO userSystemInfoVO);

}