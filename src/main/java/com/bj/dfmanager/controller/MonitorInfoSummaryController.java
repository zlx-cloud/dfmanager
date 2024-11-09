package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.MonitorInfoSummaryService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.model.ModelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 监控信息汇总
 */
@RestController
@RequestMapping("/monitorInfoSummary")
@Slf4j
public class MonitorInfoSummaryController {

    @Resource
    private MonitorInfoSummaryService monitorInfoSummaryService;

    /**
     * 异常发生
     */
    @PostMapping("/errorInfo")
    @ResponseBody
    public Result errorInfo() {
        Result result = monitorInfoSummaryService.errorInfo();
        log.info("异常发生，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 访问频次
     */
    @PostMapping("/frequency")
    @ResponseBody
    public Result frequency() {
        Result result = monitorInfoSummaryService.frequency();
        log.info("访问频次，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 访问频次全部
     */
    @PostMapping("/frequencyAll")
    @ResponseBody
    public Result frequencyAll() {
        Result result = monitorInfoSummaryService.frequencyAll();
        log.info("访问频次全部，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 响应时长
     */
    @PostMapping("/responseTime")
    @ResponseBody
    public Result responseTime() {
        Result result = monitorInfoSummaryService.responseTime();
        log.info("响应时长，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 响应时长全部
     */
    @PostMapping("/responseTimeAll")
    @ResponseBody
    public Result responseTimeAll() {
        Result result = monitorInfoSummaryService.responseTimeAll();
        log.info("响应时长全部，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 异常信息预警
     */
    @PostMapping("/exceptionInfoWarn")
    @ResponseBody
    public Result exceptionInfoWarn() {
        Result result = monitorInfoSummaryService.exceptionInfoWarn();
        log.info("异常信息预警，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 更新已读状态
     */
    @PostMapping("/updateReadStatus")
    @ResponseBody
    public Result updateReadStatus(@RequestBody ModelVO vo) {
        Result result = monitorInfoSummaryService.updateReadStatus(vo.getId());
        log.info("更新已读状态，返回：{}", JSON.toJSONString(result));
        return result;
    }

}