package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;

public interface MenuService {

    /**
     * 查询用户菜单权限
     */
    Result userMenuPerm(Integer userId);

}