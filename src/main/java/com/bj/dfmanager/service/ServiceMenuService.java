package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.servicemenu.ServiceMenuVO;

public interface ServiceMenuService {

    /**
     * 查询用户菜单权限
     */
    Result serviceMenuTree();

    /**
     * 添加/修改用服务目录菜单
     */
    Result addOrUpdate(ServiceMenuVO serviceMenuVO);

    /**
     * 查询服务目录菜单信息
     */
    Result queryById(Integer id);

    /**
     * 删除服务目录菜单信息
     */
    Result delete(Integer id);

}