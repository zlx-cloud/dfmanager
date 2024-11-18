package com.bj.dfmanager.service.impl;

import com.bj.dfmanager.entity.MonitorTaskError;
import com.bj.dfmanager.mapper.MonitorTaskErrorMapper;
import com.bj.dfmanager.service.MonitorTaskErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class MonitorTaskErrorServiceImpl implements MonitorTaskErrorService {

    @Resource
    private MonitorTaskErrorMapper monitorTaskErrorMapper;

    @Override
    public void addMonitorTaskError(String taskCode, String taskDesc) {
        MonitorTaskError monitorTaskError = new MonitorTaskError();
        monitorTaskError.setTaskCode(taskCode);
        monitorTaskError.setTaskDesc(taskDesc);
        monitorTaskError.setMonitorStatus("0");
        monitorTaskError.setErrorDesc("服务未启动");
        monitorTaskError.setErrorTime(new Date());
        monitorTaskError.setStastus("0");
        monitorTaskErrorMapper.insert(monitorTaskError);
    }

}