package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.RoleService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.menu.MenuVO;
import com.bj.dfmanager.vo.role.RoleSearchVO;
import com.bj.dfmanager.vo.role.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 查询角色列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody RoleSearchVO roleSearchVO) {
        log.info("查询角色列表，入参：{}", JSON.toJSONString(roleSearchVO));
        Result result = roleService.queryList(roleSearchVO);
        log.info("查询角色列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 角色名是否存在
     */
    @PostMapping("/roleNameIsExist")
    @ResponseBody
    public Result roleNameIsExist(@RequestBody RoleVO roleVO) {
        log.info("角色名是否存在，入参角色名称：{}", roleVO.getRoleName());
        Result result = roleService.roleNameIsExist(roleVO.getRoleName());
        log.info("角色名是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改角色
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody RoleVO roleVO) {
        log.info("添加/修改角色，入参：{}", JSON.toJSONString(roleVO));
        Result result = roleService.addOrUpdate(roleVO);
        log.info("添加/修改角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询角色信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody RoleVO roleVO) {
        log.info("查询角色信息，入参角色ID：{}", roleVO.getRoleId());
        Result result = roleService.queryById(roleVO.getRoleId());
        log.info("查询角色信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除角色
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody RoleVO roleVO) {
        log.info("删除角色，入参角色ID：{}", roleVO.getRoleId());
        Result result = roleService.delete(roleVO.getRoleId());
        log.info("删除角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用角色
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody RoleVO roleVO) {
        log.info("启用/停用角色，入参：{}", JSON.toJSONString(roleVO));
        Result result = roleService.startOrStop(roleVO);
        log.info("启用/停用角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询角色菜单
     */
    @PostMapping("/menuTree")
    @ResponseBody
    public Result menuTree(@RequestBody RoleVO roleVO) {
        log.info("查询角色菜单，入参角色ID：{}", roleVO.getRoleId());
        Result result = roleService.menuTree(roleVO.getRoleId());
        log.info("查询角色菜单，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 角色绑定菜单
     */
    @PostMapping("/roleBindMenu")
    @ResponseBody
    public Result roleBindMenu(@RequestBody MenuVO menuVO) {
        log.info("角色绑定菜单，入参：{}", JSON.toJSONString(menuVO));
        Result result = roleService.roleBindMenu(menuVO);
        log.info("角色绑定菜单，返回：{}", JSON.toJSONString(result));
        return result;
    }

}