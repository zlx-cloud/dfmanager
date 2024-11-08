package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.tasksearch.TaskLogSearchVO;

public interface TaskLogService {

    /**
     * 查询任务日志列表
     */
    Result queryList(TaskLogSearchVO taskLogSearchVO);

    /**
     * 查询任务执行结果
     */
    Result queryTaskResult(String objectId);
}