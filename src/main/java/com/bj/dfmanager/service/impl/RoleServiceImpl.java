package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Menu;
import com.bj.dfmanager.entity.Role;
import com.bj.dfmanager.mapper.MenuMapper;
import com.bj.dfmanager.mapper.RoleMapper;
import com.bj.dfmanager.mapper.RoleMenuMapper;
import com.bj.dfmanager.mapper.UserRoleMapper;
import com.bj.dfmanager.service.RoleService;
import com.bj.dfmanager.util.MenuUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.menu.MenuVO;
import com.bj.dfmanager.vo.role.RoleSearchVO;
import com.bj.dfmanager.vo.role.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询角色列表
     */
    @Override
    public Result queryList(RoleSearchVO roleSearchVO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(roleSearchVO.getRoleName()),
                Role::getRoleName, roleSearchVO.getRoleName());
        queryWrapper.eq(StringUtils.isNotEmpty(roleSearchVO.getRoleStatus()),
                Role::getRoleStatus, roleSearchVO.getRoleStatus());
        queryWrapper.orderByDesc(Role::getCreateTime);
        IPage<Role> page = roleMapper.selectPage(new Page<>(roleSearchVO.getCurrent(),
                roleSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询角色列表成功");
    }

    /**
     * 角色名是否存在
     */
    @Override
    public Result roleNameIsExist(String roleName) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, roleName);
        int count = roleMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "角色名不存在，可以创建");
        } else {
            return Result.fail(null, "角色名存在，不可以创建");
        }
    }

    /**
     * 添加/修改角色
     */
    @Override
    public Result addOrUpdate(RoleVO roleVO) {
        int num;
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        if (null == role.getRoleId()) {
            role.setRoleStatus("Y");
            role.setCreateTime(new Date());
            num = roleMapper.insert(role);
        } else {
            role.setUpdateTime(new Date());
            num = roleMapper.updateById(role);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询角色信息
     */
    @Override
    public Result queryById(Integer roleId) {
        Role role = roleMapper.selectById(roleId);
        return Result.success(role, "查询角色信息成功");
    }

    /**
     * 删除角色
     */
    @Override
    public Result delete(Integer roleId) {
        int num = roleMapper.deleteById(roleId);
        if (num > 0) {
            // 删除角色绑定的用户
            Map map = new HashMap();
            map.put("ROLE_ID", roleId);
            userRoleMapper.deleteByMap(map);

            // 删除角色绑定的菜单
            roleMenuMapper.deleteByMap(map);
            return Result.success(null, "删除角色成功");
        } else {
            return Result.fail(null, "删除角色失败");
        }
    }

    /**
     * 启用/停用角色
     */
    @Override
    public Result startOrStop(RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        role.setUpdateTime(new Date());
        int num = roleMapper.updateById(role);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

    /**
     * 查询角色菜单
     */
    @Override
    public Result menuTree(Integer roleId) {
        // 查询菜单树
        List<Menu> menuList = menuMapper.getRoleMenu(roleId);
        List<Menu> menuTree = MenuUtils.getMenuChildren(menuList, 999);
        return Result.success(menuTree, "查询角色菜单成功");
    }

    /**
     * 角色绑定菜单
     */
    @Override
    public Result roleBindMenu(MenuVO menuVO) {
        // 先删除角色已绑定的菜单
        Map map = new HashMap();
        map.put("ROLE_ID", menuVO.getRoleId());
        roleMenuMapper.deleteByMap(map);

        // 绑定新菜单
        if (CollectionUtils.isNotEmpty(menuVO.getMenuList())) {
            roleMenuMapper.roleBindMenu(menuVO.getRoleId(), menuVO.getMenuList());
        }
        return Result.success(null, "角色绑定菜单成功");
    }

}