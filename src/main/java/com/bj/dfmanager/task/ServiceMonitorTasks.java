package com.bj.dfmanager.task;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bj.dfmanager.mapper.MonitorTaskMapper;
import com.bj.dfmanager.util.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 应用服务监控任务
 */
@Component
public class ServiceMonitorTasks {

    @Value("${dfservice.exploreUrl}")
    private String dfsExploreUrl;
    @Value("${dfdispatcher.exploreUrl}")
    private String ddsExploreUrl;
    @Value("${dftasklogservice.exploreUrl}")
    private String dtlsExploreUrl;
    @Value("${dfmodelservice.exploreUrl}")
    private String dmsExploreUrl;

    @Resource
    private MonitorTaskMapper monitorTaskMapper;

    /**
     * dfservice应用监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfServiceMonitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dfsExploreUrl);
        if(StringUtils.isNotEmpty(result)){
            monitorStatus = "1";
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfservice");
    }

    /**
     * dfDispatcher应用监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfDispatcherMonitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(ddsExploreUrl);
        if(StringUtils.isNotEmpty(result)){
            monitorStatus = "1";
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfdispatcher");
    }

    /**
     * dfTaskLogService应用监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfTaskLogServiceMonitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dtlsExploreUrl);
        if(StringUtils.isNotEmpty(result)){
            monitorStatus = "1";
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dftasklogservice");
    }

    /**
     * dfModelService应用监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfModelServiceMonitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dmsExploreUrl);
        if(StringUtils.isNotEmpty(result)){
            monitorStatus = "1";
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfmodelservice");
    }

}