package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.UserSystemInfoService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoSearchVO;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户系统信息
 */
@RestController
@RequestMapping("/userSystemInfo")
@Slf4j
public class UserSystemInfoController {

    @Resource
    private UserSystemInfoService userSystemInfoService;

    /**
     * 查询用户系统信息列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody UserSystemInfoSearchVO userSystemInfoSearchVO) {
        log.info("查询用户系统信息列表，入参：{}", JSON.toJSONString(userSystemInfoSearchVO));
        Result result = userSystemInfoService.queryList(userSystemInfoSearchVO);
        log.info("查询用户系统信息列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断系统编码是否存在
     */
    @PostMapping("/systemCodeIsExist")
    @ResponseBody
    public Result systemCodeIsExist(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("判断系统编码是否存在，入参系统编码：{}", userSystemInfoVO.getSystemCode());
        Result result = userSystemInfoService.systemCodeIsExist(userSystemInfoVO.getSystemCode());
        log.info("判断系统编码是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改用户系统信息
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("添加/修改用户系统信息，入参：{}", JSON.toJSONString(userSystemInfoVO));
        Result result = userSystemInfoService.addOrUpdate(userSystemInfoVO);
        log.info("添加/修改用户系统信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户系统信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("查询用户系统信息，入参用户系统ID：{}", userSystemInfoVO.getId());
        Result result = userSystemInfoService.queryById(userSystemInfoVO.getId());
        log.info("查询用户系统信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除用户系统信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("删除用户系统信息，入参用户ID：{}", userSystemInfoVO.getId());
        Result result = userSystemInfoService.delete(userSystemInfoVO.getId());
        log.info("删除用户系统信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用用户系统信息
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("启用/停用用户系统信息，入参：{}", JSON.toJSONString(userSystemInfoVO));
        Result result = userSystemInfoService.startOrStop(userSystemInfoVO);
        log.info("启用/停用用户系统信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户系统服务
     */
    @PostMapping("/userSystemService")
    @ResponseBody
    public Result userSystemService(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("查询用户系统服务，入参用户系统ID：{}", userSystemInfoVO.getId());
        Result result = userSystemInfoService.userSystemService(userSystemInfoVO.getId());
        log.info("查询用户系统服务，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户系统的服务字段
     */
    @PostMapping("/userSystemServiceCol")
    @ResponseBody
    public Result userSystemServiceCol(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("查询用户系统已拥有的服务字段，入参用户系统ID：{}，服务ID：{}", userSystemInfoVO.getId(), userSystemInfoVO.getServiceId());
        Result result = userSystemInfoService.userSystemServiceCol(userSystemInfoVO.getId(), userSystemInfoVO.getServiceId());
        log.info("查询用户系统已拥有的服务字段，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用户系统绑定服务
     */
    @PostMapping("/userSystemBindService")
    @ResponseBody
    public Result userSystemBindService(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("用户系统绑定服务，入参：{}", JSON.toJSONString(userSystemInfoVO));
        Result result = userSystemInfoService.userSystemBindService(userSystemInfoVO);
        log.info("用户系统绑定服务，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户系统已拥有的指标
     */
    @PostMapping("/userSystemHaveTarget")
    @ResponseBody
    public Result userSystemHaveTarget(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("查询用户系统已拥有的指标，入参用户系统ID：{}", userSystemInfoVO.getId());
        Result result = userSystemInfoService.userSystemHaveTarget(userSystemInfoVO.getId());
        log.info("查询用户系统已拥有的指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询用户系统未拥有的指标
     */
    @PostMapping("/userSystemNotHaveTarget")
    @ResponseBody
    public Result userSystemNotHaveTarget(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("查询用户系统未拥有的指标，入参用户系统ID：{}", userSystemInfoVO.getId());
        Result result = userSystemInfoService.userSystemNotHaveTarget(userSystemInfoVO.getId());
        log.info("查询用户系统未拥有的指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用户系统绑定指标
     */
    @PostMapping("/userSystemBindTarget")
    @ResponseBody
    public Result userSystemBindTarget(@RequestBody UserSystemInfoVO userSystemInfoVO) {
        log.info("用户系统绑定指标，入参：{}", JSON.toJSONString(userSystemInfoVO));
        Result result = userSystemInfoService.userSystemBindTarget(userSystemInfoVO);
        log.info("用户系统绑定指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

}
