package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.UserService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.user.UserSearchVO;
import com.bj.dfmanager.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody UserVO userVO) {
        log.info("用户登录，入参：{}", JSON.toJSONString(userVO));
        Result result = userService.login(userVO);
        log.info("用户登录，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断用户名是否存在
     */
    @PostMapping("/userNameIsExist")
    @ResponseBody
    public Result userNameIsExist(@RequestBody UserVO userVO) {
        log.info("判断用户名是否存在，入参用户名称：{}", userVO.getUserName());
        Result result = userService.userNameIsExist(userVO.getUserName());
        log.info("判断用户名是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改用户
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody UserVO userVO) {
        log.info("添加/修改用户，入参：{}", JSON.toJSONString(userVO));
        Result result = userService.addOrUpdate(userVO);
        log.info("添加/修改用户，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody UserSearchVO userSearchVO) {
        log.info("查询用户列表，入参：{}", JSON.toJSONString(userSearchVO));
        Result result = userService.queryList(userSearchVO);
        log.info("查询用户列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody UserVO userVO) {
        log.info("查询用户信息，入参用户ID：{}", userVO.getUserId());
        Result result = userService.queryById(userVO.getUserId());
        log.info("查询用户信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除用户信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody UserVO userVO) {
        log.info("删除用户信息，入参用户ID：{}", userVO.getUserId());
        Result result = userService.delete(userVO.getUserId());
        log.info("删除用户信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用用户
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody UserVO userVO) {
        log.info("启用/停用用户，入参：{}", JSON.toJSONString(userVO));
        Result result = userService.startOrStop(userVO);
        log.info("启用/停用用户，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户已拥有的角色
     */
    @PostMapping("/userHaveRole")
    @ResponseBody
    public Result userHaveRole(@RequestBody UserVO userVO) {
        log.info("查询用户已拥有的角色，入参用户ID：{}", userVO.getUserId());
        Result result = userService.userHaveRole(userVO.getUserId());
        log.info("查询用户已拥有的角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户未拥有的角色
     */
    @PostMapping("/userNotHaveRole")
    @ResponseBody
    public Result userNotHaveRole(@RequestBody UserVO userVO) {
        log.info("查询用户未拥有的角色，入参用户ID：{}", userVO.getUserId());
        Result result = userService.userNotHaveRole(userVO.getUserId());
        log.info("查询用户未拥有的角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用户绑定角色
     */
    @PostMapping("/userBindRole")
    @ResponseBody
    public Result userBindRole(@RequestBody UserVO userVO) {
        log.info("用户绑定角色，入参：{}", JSON.toJSONString(userVO));
        Result result = userService.userBindRole(userVO);
        log.info("用户绑定角色，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/userList")
    @ResponseBody
    public Result userList() {
        Result result = userService.userList();
        log.info("查询用户列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

}