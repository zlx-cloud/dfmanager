package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.ServiceMenuService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.servicemenu.ServiceMenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 服务目录菜单
 */
@RestController
@RequestMapping("/serviceMenu")
@Slf4j
public class ServiceMenuController {

    @Resource
    private ServiceMenuService serviceMenuService;

    /**
     * 服务目录菜单树
     */
    @PostMapping("/serviceMenuTree")
    @ResponseBody
    public Result userMenuPerm() {
        Result result = serviceMenuService.serviceMenuTree();
        log.info("查询用户菜单权限，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改用服务目录菜单
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody ServiceMenuVO serviceMenuVO) {
        log.info("添加/修改用服务目录菜单，入参：{}", JSON.toJSONString(serviceMenuVO));
        Result result = serviceMenuService.addOrUpdate(serviceMenuVO);
        log.info("添加/修改用服务目录菜单，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询服务目录菜单信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody ServiceMenuVO serviceMenuVO) {
        log.info("查询服务目录菜单信息，入参服务目录菜单ID：{}", serviceMenuVO.getId());
        Result result = serviceMenuService.queryById(serviceMenuVO.getId());
        log.info("查询服务目录菜单信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除服务目录菜单信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ServiceMenuVO serviceMenuVO) {
        log.info("删除服务目录菜单信息，入参服务目录菜单ID：{}", serviceMenuVO.getId());
        Result result = serviceMenuService.delete(serviceMenuVO.getId());
        log.info("删除服务目录菜单信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

}