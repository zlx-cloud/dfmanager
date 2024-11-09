package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;

public interface MonitorInfoSummaryService {

    /**
     * 异常发生
     */
    Result errorInfo();

    /**
     * 访问频次
     */
    Result frequency();

    /**
     * 访问频次全部
     */
    Result frequencyAll();

    /**
     * 响应时长
     */
    Result responseTime();

    /**
     * 响应时长全部
     */
    Result responseTimeAll();

    /**
     * 异常信息预警
     */
    Result exceptionInfoWarn();

}