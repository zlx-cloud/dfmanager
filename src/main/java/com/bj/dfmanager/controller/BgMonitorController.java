package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.BgMonitorService;
import com.bj.dfmanager.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台监控采集
 */
@RestController
@RequestMapping("/bgMonitor")
@Slf4j
public class BgMonitorController {

    @Resource
    private BgMonitorService bgMonitorService;

    /**
     * 服务节点监控
     */
    @PostMapping("/serviceNodeMonitor")
    @ResponseBody
    public Result serviceNodeMonitor() {
        Result result = bgMonitorService.serviceNodeMonitor();
        log.info("服务节点监控，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 数据窗口检测
     */
    @PostMapping("/dataMonitor")
    @ResponseBody
    public Result dataMonitor() {
        Result result = bgMonitorService.dataMonitor();
        log.info("数据窗口检测，返回：{}", JSON.toJSONString(result));
        return result;
    }

}