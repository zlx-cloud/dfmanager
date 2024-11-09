package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 监控异常信息
 */
@Data
@TableName(value = "DFMANAGER.T_MONITOR_TASK_ERROR")
public class MonitorTaskError {

    // 任务编码
    @TableField("TASK_CODE")
    private String taskCode;

    // 任务描述
    @TableField("TASK_DESC")
    private String taskDesc;

    // 任务状态
    @TableField("MONITOR_STATUS")
    private String monitorStatus;

    // 异常描述
    @TableField("ERROR_DESC")
    private String errorDesc;

    // 异常时间
    @TableField("ERROR_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date errorTime;

    // 0未读，1已读
    @TableField("STASTUS")
    private String stastus;

}