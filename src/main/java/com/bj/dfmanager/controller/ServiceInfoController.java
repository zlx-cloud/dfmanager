package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.ServiceInfoService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.service.ServiceInfoSearchVO;
import com.bj.dfmanager.vo.service.ServiceInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 服务目录
 */
@RestController
@RequestMapping("/serviceInfo")
@Slf4j
public class ServiceInfoController {

    @Resource
    private ServiceInfoService serviceInfoService;

    /**
     * 查询服务目录列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody ServiceInfoSearchVO serviceInfoSearchVO) {
        log.info("查询服务目录列表，入参：{}", JSON.toJSONString(serviceInfoSearchVO));
        Result result = serviceInfoService.queryList(serviceInfoSearchVO);
        log.info("查询服务目录列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断服务编码是否存在
     */
    @PostMapping("/serviceCodeIsExist")
    @ResponseBody
    public Result userNameIsExist(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("判断服务编码是否存在，入参服务编码：{}", serviceInfoVO.getServiceCode());
        Result result = serviceInfoService.serviceCodeIsExist(serviceInfoVO.getServiceCode());
        log.info("判断服务编码是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改服务目录
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("添加/修改服务目录，入参：{}", JSON.toJSONString(serviceInfoVO));
        Result result = serviceInfoService.addOrUpdate(serviceInfoVO);
        log.info("添加/修改服务目录，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询服务目录
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("查询服务目录信息，入参服务目录ID：{}", serviceInfoVO.getId());
        Result result = serviceInfoService.queryById(serviceInfoVO.getId());
        log.info("查询服务目录信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除服务目录
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("删除服务目录，入参服务目录ID：{}", serviceInfoVO.getId());
        Result result = serviceInfoService.delete(serviceInfoVO.getId());
        log.info("删除服务目录，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用服务目录
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("启用/停用服务目录，入参：{}", JSON.toJSONString(serviceInfoVO));
        Result result = serviceInfoService.startOrStop(serviceInfoVO);
        log.info("启用/停用服务目录，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询启用状态下服务接口
     */
    @PostMapping("/queryEnableService")
    @ResponseBody
    public Result queryEnableService(@RequestBody ServiceInfoSearchVO serviceInfoSearchVO) {
        log.info("查询启用状态下服务接口，入参：{}", JSON.toJSONString(serviceInfoSearchVO));
        Result result = serviceInfoService.queryEnableService(serviceInfoSearchVO);
        log.info("查询启用状态下服务接口，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 接口探测
     */
    @PostMapping("/serviceTest")
    @ResponseBody
    public Result serviceTest(@RequestBody ServiceInfoVO serviceInfoVO) {
        log.info("接口探测，入参 服务ID：{}", JSON.toJSONString(serviceInfoVO.getId()));
        Result result = serviceInfoService.serviceTest(serviceInfoVO.getId());
        log.info("接口探测，返回：{}", JSON.toJSONString(result));
        return result;
    }

}