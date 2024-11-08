package com.bj.dfmanager.service.impl;

import com.bj.dfmanager.entity.Menu;
import com.bj.dfmanager.mapper.MenuMapper;
import com.bj.dfmanager.service.MenuService;
import com.bj.dfmanager.util.MenuUtils;
import com.bj.dfmanager.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 查询用户菜单权限
     */
    @Override
    public Result userMenuPerm(Integer userId) {
        // 查询用户菜单
        List<Menu> menuList = menuMapper.getUserMenu(userId);
        // 生成菜单树
        List<Menu> menuTree = MenuUtils.getMenuChildren(menuList, 999);
        return Result.success(menuTree, "查询用户菜单权限成功");
    }

}