package com.bj.dfmanager.service.impl;

import com.bj.dfmanager.entity.ModelDemandLog;
import com.bj.dfmanager.mapper.ModelDemandLogMapper;
import com.bj.dfmanager.service.ModelDemandLogService;
import com.bj.dfmanager.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class ModelDemandLogServiceImpl implements ModelDemandLogService {

    @Resource
    private ModelDemandLogMapper modelDemandLogMapper;

    @Override
    public void addLog(int modelDemandId, String operateAction) {
        ModelDemandLog log = new ModelDemandLog();
        log.setModelDemandId(modelDemandId);
        log.setOperateAction(operateAction);
        log.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.setOperateUser(JwtTokenUtils.getCurrentUser().getUserId());
        modelDemandLogMapper.insert(log);
    }

}
