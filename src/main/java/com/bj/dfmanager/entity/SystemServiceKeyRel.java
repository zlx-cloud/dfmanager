package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DFMANAGER.T_SYSTEM_SERVICE_KEY_REL")
public class SystemServiceKeyRel {

    @TableField("SYSTEM_ID")
    private Integer systemId;

    @TableField("SERVICE_ID")
    private Integer serviceId;

    @TableField("KEY_NAME")
    private String keyName;

}
