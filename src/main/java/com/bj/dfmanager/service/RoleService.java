package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.menu.MenuVO;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.role.RoleSearchVO;
import com.bj.dfmanager.vo.role.RoleVO;

public interface RoleService {

    /**
     * 查询角色列表
     */
    Result queryList(RoleSearchVO roleSearchVO);

    /**
     * 角色名是否存在
     */
    Result roleNameIsExist(String roleName);

    /**
     * 添加/修改角色
     */
    Result addOrUpdate(RoleVO roleVO);

    /**
     * 查询角色信息
     */
    Result queryById(Integer roleId);

    /**
     * 删除角色
     */
    Result delete(Integer roleId);

    /**
     * 启用/停用角色
     */
    Result startOrStop(RoleVO roleVO);

    /**
     * 查询角色菜单
     */
    Result menuTree(Integer roleId);

    /**
     * 角色绑定菜单
     */
    Result roleBindMenu(MenuVO menuVO);

}