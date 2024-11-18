package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 监控任务表
 */
@Data
@TableName(value = "DFMANAGER.T_MONITOR_TASK")
public class MonitorTask {

    // 任务编码
    @TableField("TASK_CODE")
    private String taskCode;

    // 任务描述
    @TableField("TASK_DESC")
    private String taskDesc;

    // 任务状态
    @TableField("MONITOR_STATUS")
    private String monitorStatus;

    // 任务用时
    @TableField("TASK_TIME")
    private Integer taskTime;

    // 排序序号
    @TableField("SORT")
    private Integer sort;

    // 任务最新执行时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    @TableField("IP")
    private String ip;

    @TableField("PORT")
    private Integer port;

    @TableField("TYPE")
    private String type;

}