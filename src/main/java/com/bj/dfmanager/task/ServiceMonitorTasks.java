package com.bj.dfmanager.task;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bj.dfmanager.mapper.MonitorTaskMapper;
import com.bj.dfmanager.service.MonitorTaskErrorService;
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

    @Resource
    private MonitorTaskMapper monitorTaskMapper;
    @Resource
    private MonitorTaskErrorService monitorTaskErrorService;

    //============================================ 142原有 ============================================
    @Value("${dfservice.exploreUrl}")
    private String dfsExploreUrl;
    @Value("${dfdispatcher.exploreUrl}")
    private String ddsExploreUrl;
    @Value("${dftasklogservice.exploreUrl}")
    private String dtlsExploreUrl;
    @Value("${dfmodelservice.exploreUrl}")
    private String dmsExploreUrl;

    /**
     * dfservice应用监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfServiceMonitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dfsExploreUrl);
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfservice", "dfService");
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
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfdispatcher", "dfDispatcher");
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
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dftasklogservice", "dfTaskLogService");
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
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfmodelservice", "dfModelService");
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfmodelservice");
    }

    //============================================ 142新 ============================================
    @Value("${dfqueryservice.ip}")
    private String dfQueryServiceIp;
    @Value("${dfqueryservice.port}")
    private Integer dfQueryServicePort;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfQueryServiceMonitor() {
        // 任务状态
        String monitorStatus = "0";

        if (HttpUtils.isPortOpen(dfQueryServiceIp, dfQueryServicePort)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfqueryservice", "dfQueryService");
        }

        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfqueryservice");
    }

    //============================================ 141 ============================================
    @Value("${dfmanager141.ip}")
    private String dfManager141Ip;
    @Value("${dfmanager141.port}")
    private Integer dfManager141Port;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfManager141Monitor() {
        // 任务状态
        String monitorStatus = "0";

        if (HttpUtils.isPortOpen(dfManager141Ip, dfManager141Port)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfmanager141", "dfManager141");
        }

        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfmanager141");
    }

    @Value("${dataqueryservice141.ip}")
    private String dataQueryService141Ip;
    @Value("${dataqueryservice141.port}")
    private Integer dataQueryService141Port;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dataQueryService141Monitor() {
        // 任务状态
        String monitorStatus = "0";

        if (HttpUtils.isPortOpen(dataQueryService141Ip, dataQueryService141Port)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dataqueryservice141", "dataQueryService141");
        }

        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dataqueryservice141");
    }

    @Value("${dfqueryservice141.ip}")
    private String dfQueryService141Ip;
    @Value("${dfqueryservice141.port}")
    private Integer dfQueryService141Port;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfQueryService141Monitor() {
        // 任务状态
        String monitorStatus = "0";

        if (HttpUtils.isPortOpen(dfQueryService141Ip, dfQueryService141Port)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfqueryservice141", "dfQueryService141");
        }

        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfqueryservice141");
    }

    //============================================ 143新 ============================================
    @Value("${dftasklogservice143.exploreUrl}")
    private String dtls143ExploreUrl;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfTaskLogService143Monitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dtls143ExploreUrl);
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dftasklogservice143", "dfTaskLogService143");
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dftasklogservice143");
    }

    @Value("${dfdispatcher143.exploreUrl}")
    private String dds143ExploreUrl;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfDispatcher143Monitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dds143ExploreUrl);
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfdispatcher143", "dfDispatcher143");
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfdispatcher143");
    }

    @Value("${dfmodelservice143.exploreUrl}")
    private String dms143ExploreUrl;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfModelService143Monitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dms143ExploreUrl);
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfmodelservice143", "dfModelService143");
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfmodelservice143");
    }

    @Value("${dfservice143.exploreUrl}")
    private String dfs143ExploreUrl;

    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void dfService143Monitor() {
        // 任务状态
        String monitorStatus = "0";

        String result = HttpUtils.doGet(dfs143ExploreUrl);
        if (StringUtils.isNotEmpty(result)) {
            monitorStatus = "1";
        } else {
            monitorTaskErrorService.addMonitorTaskError("dfservice143", "dfService143");
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "dfservice143");
    }

}