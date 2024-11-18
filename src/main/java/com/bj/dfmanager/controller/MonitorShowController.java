package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.MonitorShowService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.model.ModelSearchVO;
import com.bj.dfmanager.vo.monitor.MonitorDiskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 监控台展板
 */
@RestController
@RequestMapping("/monitorShow")
@Slf4j
public class MonitorShowController {

    @Resource
    private MonitorShowService monitorShowService;

    /**
     * 启用指标数量
     */
    @PostMapping("/startTargetCount")
    @ResponseBody
    public Result startTargetCount() {
        Result result = monitorShowService.startTargetCount();
        log.info("查询启用指标数量，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用指标列表
     */
    @PostMapping("/startTargetList")
    @ResponseBody
    public Result startTargetList() {
        Result result = monitorShowService.startTargetList();
        log.info("查询启用指标列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 停用指标数量
     */
    @PostMapping("/stopTargetCount")
    @ResponseBody
    public Result stopTargetCount() {
        Result result = monitorShowService.stopTargetCount();
        log.info("查询停用指标数量，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 停用指标列表
     */
    @PostMapping("/stopTargetList")
    @ResponseBody
    public Result stopTargetList() {
        Result result = monitorShowService.stopTargetList();
        log.info("查询停用指标列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 当日服务次数
     */
    @PostMapping("/todayServiceCount")
    @ResponseBody
    public Result todayServiceCount() {
        Result result = monitorShowService.todayServiceCount();
        log.info("查询当日服务次数，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 模型列表
     */
    @PostMapping("/modelList")
    @ResponseBody
    public Result modelList() {
        Result result = monitorShowService.modelList();
        log.info("查询模型列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 特定模型当日服务次数
     */
    @PostMapping("/modelTodayServiceCount")
    @ResponseBody
    public Result modelTodayServiceCount(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询特定模型当日服务次数，入参：{}", modelSearchVO.getId());
        Result result = monitorShowService.modelTodayServiceCount(modelSearchVO.getId());
        log.info("查询特定模型当日服务次数，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 服务次数趋势
     */
    @PostMapping("/modelCountTrend")
    @ResponseBody
    public Result modelCountTrend(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询服务次数趋势，入参：{}", modelSearchVO.getId());
        Result result = monitorShowService.modelCountTrend(modelSearchVO.getId());
        log.info("查询服务次数趋势，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 响应时长趋势
     */
    @PostMapping("/modelTimeTrend")
    @ResponseBody
    public Result modelTimeTrend(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询响应时长趋势，入参：{}", modelSearchVO.getId());
        Result result = monitorShowService.modelTimeTrend(modelSearchVO.getId());
        log.info("查询响应时长趋势，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 监控接口的正确率和错误率
     */
    @PostMapping("/modelResultRate")
    @ResponseBody
    public Result modelResultRate() {
        Result result = monitorShowService.modelResultRate();
        log.info("监控接口的正确率和错误率，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 磁盘监控
     */
    @PostMapping("/diskMonitor")
    @ResponseBody
    public Result diskMonitor() {
        Result result = monitorShowService.diskMonitor();
        log.info("磁盘监控，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 更新磁盘监控
     */
    @PostMapping("/updateDiskMonitor")
    @ResponseBody
    public Result updateDiskMonitor(@RequestBody MonitorDiskVO vo) {
        Result result = monitorShowService.updateDiskMonitor(vo);
        log.info("更新磁盘监控，返回：{}", JSON.toJSONString(result));
        return result;
    }

}