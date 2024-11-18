package com.bj.dfmanager.task;

import com.bj.dfmanager.mapper.MonitorTaskMapper;
import com.bj.dfmanager.service.MonitorTaskErrorService;
import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.params.ConnectParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据库监控任务
 */
@Component
public class DbMonitorTasks {

    @Value("${hybase.url}")
    private String hybaseUrl;
    @Value("${hybase.username}")
    private String hybaseUserName;
    @Value("${hybase.password}")
    private String hybasePassword;

    @Resource
    private MonitorTaskMapper monitorTaskMapper;
    @Resource
    private MonitorTaskErrorService monitorTaskErrorService;

    private TRSConnection trsConn = null;

    private TRSConnection getTrsConn() {
        if (trsConn == null) {
            ConnectParams connParams = new ConnectParams();
            connParams.setTimeout(5000L);
            trsConn = new TRSConnection(hybaseUrl, hybaseUserName, hybasePassword, connParams);
        }
        return trsConn;
    }

    private void closeTrsConn() {
        if (trsConn != null) {
            trsConn.close();
            trsConn = null;
        }
    }

    /**
     * 海贝连接时长
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void hybaseMonitor() {
        // 任务状态
        String monitorStatus = "0";
        // 任务用时
        String taskTime;
        try {
            long startTime = System.currentTimeMillis();
            getTrsConn().getDatabases();
            long endTime = System.currentTimeMillis();
            monitorStatus = "1";
            taskTime = (endTime - startTime) + "";
        } catch (Exception e) {
            e.printStackTrace();
            taskTime = null;
            monitorTaskErrorService.addMonitorTaskError("hybase", "海贝");
        } finally {
            closeTrsConn();
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, taskTime, "hybase");
    }

    /**
     * 达梦数据库监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dmMonitor() {
        try {
            monitorTaskMapper.queryCountByTaskCode("dm");
            monitorTaskMapper.updateMonitorInfo("1", null, "dm");
        } catch (Exception e) {
            e.printStackTrace();
            // monitorTaskErrorService.addMonitorTaskError("dm", "达梦数据库");
        }
    }

}