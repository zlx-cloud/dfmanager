package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户系统信息 指标 关系表
 */
@Data
@TableName(value = "DFMANAGER.T_SYSTEM_TARGET_REL")
public class UserSystemTarget {

    // 指标ID
    @TableField("TARGET_ID")
    private Integer targetId;

    // 用户系统信息ID
    @TableField("SYSTEM_ID")
    private Integer systemId;

}