package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;

public interface BgMonitorService {

    /**
     * 服务节点监控
     */
    Result serviceNodeMonitor();

    /**
     * 数据窗口检测
     */
    Result dataMonitor();

}