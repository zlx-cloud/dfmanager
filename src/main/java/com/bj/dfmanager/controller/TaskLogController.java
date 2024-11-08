package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.TaskLogService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.tasksearch.TaskLogSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 任务日志
 */
@RestController
@RequestMapping("/taskLog")
@Slf4j
public class TaskLogController {

    @Resource
    private TaskLogService taskLogService;

    /**
     * 查询任务日志列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody TaskLogSearchVO taskLogSearchVO) {
        log.info("查询任务日志列表，入参：{}", JSON.toJSONString(taskLogSearchVO));
        Result result = taskLogService.queryList(taskLogSearchVO);
        log.info("查询任务日志列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询任务执行结果
     */
    @PostMapping("/queryTaskResult")
    @ResponseBody
    public Result queryTaskResult(@RequestBody TaskLogSearchVO taskLogSearchVO) {
        log.info("查询任务执行结果，入参 OBJECTID：{}", taskLogSearchVO.getObjectId());
        Result result = taskLogService.queryTaskResult(taskLogSearchVO.getObjectId());
        log.info("查询任务执行结果，返回：{}", JSON.toJSONString(result));
        return result;
    }

}