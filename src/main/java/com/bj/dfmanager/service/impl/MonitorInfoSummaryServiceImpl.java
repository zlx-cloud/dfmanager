package com.bj.dfmanager.service.impl;

import com.bj.dfmanager.entity.MonitorTaskError;
import com.bj.dfmanager.mapper.MonitorTaskErrorMapper;
import com.bj.dfmanager.service.MonitorInfoSummaryService;
import com.bj.dfmanager.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MonitorInfoSummaryServiceImpl implements MonitorInfoSummaryService {

    @Resource
    private MonitorTaskErrorMapper monitorTaskErrorMapper;

    @Override
    public Result errorInfo() {
        List<Map<String, Object>> list = monitorTaskErrorMapper.errorInfo();
        return Result.success(list, "查询异常发生成功");
    }

    @Override
    public Result frequency() {
        List<Map<String, Object>> list = monitorTaskErrorMapper.frequency();
        return Result.success(list, "查询访问频次成功");
    }

    @Override
    public Result frequencyAll() {
        List<Map<String, Object>> list = monitorTaskErrorMapper.frequencyAll();
        return Result.success(list, "查询访问频次全部成功");
    }

    @Override
    public Result responseTime() {
        List<Map<String, Object>> list = monitorTaskErrorMapper.responseTime();
        return Result.success(list, "查询响应时长成功");
    }

    @Override
    public Result responseTimeAll() {
        List<Map<String, Object>> list = monitorTaskErrorMapper.responseTimeAll();
        return Result.success(list, "查询响应时长全部成功");
    }

    @Override
    public Result exceptionInfoWarn() {
        List<MonitorTaskError> list = monitorTaskErrorMapper.exceptionInfoWarn();
        return Result.success(list, "查询异常信息预警成功");
    }

    @Override
    public Result updateReadStatus(Integer id) {
        monitorTaskErrorMapper.updateReadStatus(id);
        return Result.success(null, "更新已读状态成功");
    }

}