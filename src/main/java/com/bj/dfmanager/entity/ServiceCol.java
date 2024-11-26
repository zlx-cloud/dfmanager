package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DFMANAGER.T_SERVICE_COL")
public class ServiceCol {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("SERVICE_ID")
    private Integer serviceId;

    @TableField("KEY_CODE")
    private String keyCode;

    @TableField("KEY_NAME")
    private String keyName;

    @TableField("KEY_TYPE")
    private String keyType;

    @TableField("SERVICE_CODE")
    private String serviceCode;

    @TableField("SERVICE_NAME")
    private String serviceName;

    // 是否选中
    @TableField(exist = false)
    private Boolean checked;

}
