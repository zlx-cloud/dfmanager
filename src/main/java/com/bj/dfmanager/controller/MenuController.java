package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.MenuService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询用户菜单权限
     */
    @PostMapping("/userMenuPerm")
    @ResponseBody
    public Result userMenuPerm(@RequestBody UserVO userVO) {
        log.info("查询用户菜单权限，入参用户ID：{}", userVO.getUserId());
        Result result = menuService.userMenuPerm(userVO.getUserId());
        log.info("查询用户菜单权限，返回：{}", JSON.toJSONString(result));
        return result;
    }


}
