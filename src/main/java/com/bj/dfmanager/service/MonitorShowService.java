package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.monitor.MonitorDiskVO;

public interface MonitorShowService {

    /**
     * 启用指标数量
     */
    Result startTargetCount();

    /**
     * 启用指标列表
     */
    Result startTargetList();

    /**
     * 停用指标数量
     */
    Result stopTargetCount();

    /**
     * 停用指标列表
     */
    Result stopTargetList();

    /**
     * 当日服务次数
     */
    Result todayServiceCount();

    /**
     * 模型列表
     */
    Result modelList();

    /**
     * 特定模型当日服务次数
     */
    Result modelTodayServiceCount(String id);

    /**
     * 服务次数趋势
     */
    Result modelCountTrend(String id);

    /**
     * 响应时长趋势
     */
    Result modelTimeTrend(String id);

    /**
     * 监控接口的正确率和错误率
     */
    Result modelResultRate();

    /**
     * 磁盘监控
     */
    Result diskMonitor();

    /**
     * 更新磁盘监控
     */
    Result updateDiskMonitor(MonitorDiskVO vo);

}