package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 磁盘监控
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL_TARGET")
public class MonitorDisk {

    @TableField("IP")
    private String ip;

    @TableField("TOTAL")
    private String total;

    @TableField("USED")
    private String used;

    @TableField("FREE")
    private String free;

}