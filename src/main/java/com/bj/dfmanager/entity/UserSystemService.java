package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户系统信息 服务 关系表
 */
@Data
@TableName(value = "DFMANAGER.T_SYSTEM_SERVICE_REL")
public class UserSystemService {

    // 服务ID
    @TableField("SERVICE_ID")
    private Integer serviceId;

    // 用户系统信息ID
    @TableField("SYSTEM_ID")
    private Integer systemId;

}